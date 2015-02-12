package com.sagetech.conference_android.app.util;


import android.app.Application;

import com.sagetech.conference_android.app.ConferenceApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                ConferenceApplication.class
        }
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

}
