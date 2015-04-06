package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.PresenterData;
import com.sagetech.conference_android.app.model.RoomData;
import com.sagetech.conference_android.app.ui.viewModel.EventDetailViewModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by carlushenry on 2/19/15.
 */
public class EventDetailActivityPresenter implements IEventDetailPresenter {

    private final IEventDetailActivity eventDetailActivity;
    private final ConferenceController conferenceController;
    private Subscription subscription;


    public EventDetailActivityPresenter(IEventDetailActivity eventDetailActivity, ConferenceController conferenceController) {
        this.eventDetailActivity = eventDetailActivity;
        this.conferenceController = conferenceController;
    }

    public void initialize(Long eventId) {
        Observable<EventDetailViewModel> eventDetailViewObservable = createEventDetailViewObservable(eventId);


        subscription = eventDetailViewObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EventDetailViewModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("Error occurred...");
                    }

                    @Override
                    public void onNext(EventDetailViewModel eventDetailViewModel) {
                        eventDetailActivity.populateWithEventDetailView(eventDetailViewModel);
                    }
                });

    }

    private Observable<EventDetailViewModel> createEventDetailViewObservable(Long eventId) {
        // A - we only want to call this data one time...therefore we are caching
        // FIXME this should use eventId, but we have to wait until we are being passed valid values.
        final Observable<ConferenceSessionData> conferenceSessionObservable =
                conferenceController.getConferenceSessionDataById(eventId).cache();


        // B - retrieve all of the presenters for this
        Observable<List<PresenterData>> presenterObservable =
                conferenceSessionObservable.flatMap(new Func1<ConferenceSessionData, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(ConferenceSessionData conferenceSessionData) {
                        return Observable.from(conferenceSessionData.getPresenterIds());
                    }
                }).flatMap(new Func1<Long, Observable<PresenterData>>() {
                    @Override
                    public Observable<PresenterData> call(Long presenterId) {
                        return conferenceController.getPresenterById(presenterId);
                    }
                }).toList();

        // C - retrieve the specific room
        Observable<RoomData> roomDataObservable =
                conferenceSessionObservable.flatMap(new Func1<ConferenceSessionData, Observable<RoomData>>() {
                    @Override
                    public Observable<RoomData> call(ConferenceSessionData conferenceSessionData) {
                        try {
                            return conferenceController.getRoomById(conferenceSessionData.getRoomId());
                        } catch (Exception e) {
                            Timber.e(String.format("Error occurred finding room: %d", conferenceSessionData.getRoomId()), e);
                        }
                        return Observable.just(new RoomData());
                    }
                }).onErrorReturn(new Func1<Throwable, RoomData>() {
                    @Override
                    public RoomData call(Throwable throwable) {
                        Timber.w("Exception thrown when trying to retrieve RoomData.  Will default to empty RoomData", throwable);
                        return new RoomData();
                    }
                });

        // Combine all of the observers results together into one EventDetailViewModel
        return Observable.zip(conferenceSessionObservable, presenterObservable, roomDataObservable, new Func3<ConferenceSessionData, List<PresenterData>, RoomData, EventDetailViewModel>() {
            @Override
            public EventDetailViewModel call(ConferenceSessionData confSessionData, List<PresenterData> presenterDataList, RoomData roomData) {
                return new EventDetailViewModel(confSessionData, roomData, presenterDataList);
            }
        });
    }

    public void onDestroy() {
        subscription.unsubscribe();
    }

}
