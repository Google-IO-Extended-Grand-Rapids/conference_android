package com.sagetech.conference_android.app.util;


import android.app.Application;

import com.sagetech.conference_android.app.ConferenceApplication;
import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.EventData;
import com.sagetech.conference_android.app.ui.activities.EventDetailActivity;
import com.sagetech.conference_android.app.ui.activities.MainActivity;
import com.sagetech.conference_android.app.ui.activities.SplashActivity;
import com.sagetech.conference_android.app.ui.fragments.FullScheduleFragment;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Module(
        injects = {
                ConferenceApplication.class,
                SplashActivity.class,
                MainActivity.class,
                FullScheduleFragment.class,
                EventDetailActivity.class
        }, library = true
)
public final class ConferenceModule {

    private final ConferenceApplication app;

    public ConferenceModule(ConferenceApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    ConferenceController provideConferenceController() {
        return new ConferenceController();
    }

    @Provides
    @Singleton
    Observable<List<EventData>> provideCachedGetEventsObservable(ConferenceController conferenceController) {
        return conferenceController.getEventsObservable().cache();
    }

}
