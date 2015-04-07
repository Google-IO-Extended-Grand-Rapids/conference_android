package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.RoomData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;

import java.util.List;
import java.util.Map;

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
public class ConferenceSessionListActivityPresenter implements IConferenceSessionListPresenter {
    private IConferenceSessionActivity conferenceSessionListActivity;
    private ConferenceController conferenceController;
    private Subscription subscription;

    public ConferenceSessionListActivityPresenter(IConferenceSessionActivity conferenceSessionListActivity, ConferenceController conferenceController) {
        this.conferenceSessionListActivity = conferenceSessionListActivity;
        this.conferenceController = conferenceController;
    }

    public void initialize(Long conferenceId) {
        Observable<List<ConferenceSessionViewModel>> conferenceDataObservable = createConferenceSessionViewModelObservable(conferenceId);

        subscription = conferenceDataObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(populateConferenceSessionListSubscriber());
    }

    private Subscriber<List<ConferenceSessionViewModel>> populateConferenceSessionListSubscriber() {
        return new Subscriber<List<ConferenceSessionViewModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "Error in ConferenceSessionListActivityPresenter", (Object) null);

            }

            @Override
            public void onNext(List<ConferenceSessionViewModel> conferenceSessionViewModels) {
                conferenceSessionListActivity.populateConferenceSessions(conferenceSessionViewModels);
            }

        };
    }

    private Observable<List<ConferenceSessionViewModel>> createConferenceSessionViewModelObservable(Long conferenceId) {

        Observable<List<ConferenceSessionData>> conferenceSessionObservable = conferenceController.getConferenceSessionsById(conferenceId).cache();

        Observable<Map<Long, RoomData>> roomDataObservable = conferenceSessionObservable.flatMap(new Func1<List<ConferenceSessionData>, Observable<ConferenceSessionData>>() {
            @Override
            public Observable<ConferenceSessionData> call(List<ConferenceSessionData> conferenceSessionDatas) {
                return Observable.from(conferenceSessionDatas);
            }
        }).map(new Func1<ConferenceSessionData, Long>() {

            @Override
            public Long call(ConferenceSessionData conferenceSessionData) {
                return conferenceSessionData.getRoomId();
            }

        })
                .distinct()
                .flatMap(new Func1<Long, Observable<RoomData>>() {
                    @Override
                    public Observable<RoomData> call(Long roomId) {
                        return conferenceController.getRoomById(roomId);
                    }
                }).onErrorReturn(new Func1<Throwable, RoomData>() {

                    @Override
                    public RoomData call(Throwable throwable) {
                        RoomData rd = new RoomData();
                        rd.setConferenceId(null);
                        rd.setFullDesc("Full description");
                        rd.setId(null); // this will be the default roomData
                        rd.setShortDesc("Unknown");
                        return rd;
                    }
                }).toMap(new Func1<RoomData, Long>() {
                    @Override
                    public Long call(RoomData roomData) {
                        return roomData.getId();
                    }
                });

        return Observable.zip(conferenceSessionObservable, roomDataObservable, new Func2<List<ConferenceSessionData>, Map<Long, RoomData>, List<ConferenceSessionViewModel>>() {
            @Override
            public List<ConferenceSessionViewModel> call(List<ConferenceSessionData> conferenceSessionDatas, Map<Long, RoomData> roomDatas) {
                return new ConferenceSessionViewBuilder().toConferenceSessionViewModel(conferenceSessionDatas, roomDatas);
            }
        });
    }


    public void onDestroy() {
        subscription.unsubscribe();
    }

}
