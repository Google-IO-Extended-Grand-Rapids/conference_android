package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.EventData;
import com.sagetech.conference_android.app.ui.viewModel.EventDetailView;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
        Observable<ConferenceSessionData> conferenceSessionObservable = conferenceController.getConferenceSessionDataById(51L);
        conferenceSessionObservable.flatMap(new Func1<ConferenceSessionData, Observable<?>>() {
            @Override
            public Observable<?> call(ConferenceSessionData conferenceSessionData) {
                // create observable for presenter data
                // create observable for room data

                // combine both of these observables (probably with zip or flatmap) http://blog.danlew.net/2014/09/22/grokking-rxjava-part-2/
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
