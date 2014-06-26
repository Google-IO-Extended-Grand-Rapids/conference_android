package com.sagetech.conference_android.app;

import android.app.Application;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.EventData;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by carlushenry on 5/28/14.
 */
public class ConferenceApplication extends Application {
    private ConferenceController conferenceController;
    private Observable<List<EventData>> cachedGetEventsObservable;

    @Override
    public void onCreate() {
        super.onCreate();
        this.conferenceController = new ConferenceController();
        this.cachedGetEventsObservable = conferenceController.getEventsObservable().cache();
    }

    public ConferenceController getConferenceController() {
        return conferenceController;
    }

    public Observable<List<EventData>> getCachedGetEventsObservable() {
        return cachedGetEventsObservable;
    }
}
