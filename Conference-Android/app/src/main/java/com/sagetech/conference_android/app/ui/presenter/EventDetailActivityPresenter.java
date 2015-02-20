package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.PresenterData;
import com.sagetech.conference_android.app.model.RoomData;
import com.sagetech.conference_android.app.ui.viewModel.EventDetailView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/**
 * Created by carlushenry on 2/19/15.
 */
public class EventDetailActivityPresenter {

    private final IEventDetailActivity eventDetailActivity;
    private final ConferenceController conferenceController;
    private final Integer eventId;
    private final EventDetailViewBuilder eventDetailViewBuilder;
    private Subscription subscription;


    public EventDetailActivityPresenter(IEventDetailActivity eventDetailActivity, ConferenceController conferenceController, Integer eventId) {
        this.eventDetailActivity = eventDetailActivity;
        this.conferenceController = conferenceController;
        this.eventId = eventId;
        this.eventDetailViewBuilder = new EventDetailViewBuilder();
    }

    public void initialize() {

        // A - we only want to call this data one time...therefore we are caching
        final Observable<ConferenceSessionData> conferenceSessionObservable =
                conferenceController.getConferenceSessionDataById(51L).cache();


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
                        return conferenceController.getRoomById(conferenceSessionData.getRoomId());
                    }
                });

        Observable<EventDetailView> eventDetailViewObservable = Observable.zip(conferenceSessionObservable, presenterObservable, roomDataObservable, new Func3<ConferenceSessionData, List<PresenterData>, RoomData, EventDetailView>() {
            @Override
            public EventDetailView call(ConferenceSessionData confSessionData, List<PresenterData> presentDataList, RoomData roomData) {
                return null;
            }
        });

        subscription = conferenceSessionObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new PopulateEventDetailViewSubscriber());
    }

    private void populateConferenceSessionData(ConferenceSessionData conferenceSessionData) {
        EventDetailView eventDetailView = eventDetailViewBuilder.toEventDetailView(conferenceSessionData);
        eventDetailActivity.populateWithEventDetailView(eventDetailView);
    }

    public void onDestroy() {
        subscription.unsubscribe();
    }


    private class PopulateEventDetailViewSubscriber extends Subscriber<ConferenceSessionData> {
        private ConferenceSessionData conferenceSessionData;

        @Override
        public void onCompleted() {
            populateConferenceSessionData(conferenceSessionData);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ConferenceSessionData conferenceSessionData) {
            this.conferenceSessionData = conferenceSessionData;
        }
    }

}
