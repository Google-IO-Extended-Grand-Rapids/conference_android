package com.sagetech.conference_android.app.ui.presenter;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.RoomData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by adam on 2/21/15.
 */
public class ConferenceSessionListActivityPresenter {
    private IConferenceSessionActivity conferenceSessionListActivity;
    private ConferenceController conferenceController;
    private Integer conferenceId;
    private Subscription subscription;

    public ConferenceSessionListActivityPresenter(IConferenceSessionActivity conferenceSessionListActivity, ConferenceController conferenceController, Integer conferenceId) {
        this.conferenceSessionListActivity = conferenceSessionListActivity;
        this.conferenceController = conferenceController;
        this.conferenceId = conferenceId;
    }

    public void initialize() {
        Observable<List<ConferenceSessionViewModel>> conferenceDataObservable = createConferenceSessionViewModelObservable();

        subscription = conferenceDataObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<ConferenceSessionViewModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "Error in ConferenceSessionListActivityPresenter", null);

            }

            @Override
            public void onNext(List<ConferenceSessionViewModel> conferenceSessionDatas) {
                conferenceSessionListActivity.populateConferenceSessions(conferenceSessionDatas);
            }
        });
    }

    private Observable<List<ConferenceSessionViewModel>> createConferenceSessionViewModelObservable() {

        Observable<List<ConferenceSessionData>> conferenceSessionObservable = conferenceController.getConferenceSessionsById(conferenceId).cache();


        Observable<List<RoomData>> roomDataObservable = conferenceSessionObservable.flatMap(new Func1<List<ConferenceSessionData>, Observable<Long>>() {
            @Override
            public Observable<Long> call(List<ConferenceSessionData> conferenceSessionDatas) {
                return Observable.from(Collections2.transform(conferenceSessionDatas, new Function<ConferenceSessionData, Long>() {
                    @Override
                    public Long apply(ConferenceSessionData conferenceSessionData) {
                        return conferenceSessionData.getRoomId();
                    }
                }));
            }
        }).flatMap(new Func1<Long, Observable<RoomData>>() {
            @Override
            public Observable<RoomData> call(Long roomId) {
                return conferenceController.getRoomById(roomId);
            }
        }).toList();

        return Observable.zip(conferenceSessionObservable, roomDataObservable, new Func2<List<ConferenceSessionData>, List<RoomData>, List<ConferenceSessionViewModel>>() {
            @Override
            public List<ConferenceSessionViewModel> call(List<ConferenceSessionData> conferenceSessionDatas, List<RoomData> roomDatas) {
                return new ConferenceSessionViewBuilder().build(conferenceSessionDatas, roomDatas);
            }
        });
    }

    public void onDestroy() {
        subscription.unsubscribe();
    }
}
