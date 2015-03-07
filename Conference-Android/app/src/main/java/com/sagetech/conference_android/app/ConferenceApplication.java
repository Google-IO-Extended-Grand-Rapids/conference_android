package com.sagetech.conference_android.app;

import android.app.Application;
import android.content.Context;

import com.sagetech.conference_android.app.util.ConferenceModule;

import dagger.ObjectGraph;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;
import static timber.log.Timber.HollowTree;

/**
 * Created by carlushenry on 5/28/14.
 */
public class ConferenceApplication extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        } else {
            Timber.plant(new HollowTree());
        }

        buildObjectGraphAndInject();
    }

    public void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(new ConferenceModule(this));
        objectGraph.inject(this);
    }

    public void inject(Object o) {
        objectGraph.inject(o);
    }

    public static ConferenceApplication get(Context context) {
        return (ConferenceApplication) context.getApplicationContext();
    }

}
