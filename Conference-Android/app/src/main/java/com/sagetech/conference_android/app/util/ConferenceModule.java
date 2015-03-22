package com.sagetech.conference_android.app.util;


import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sagetech.conference_android.app.BuildConfig;
import com.sagetech.conference_android.app.ConferenceApplication;
import com.sagetech.conference_android.app.api.ConferenceApi;
import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.ui.activities.ConferenceListActivity;
import com.sagetech.conference_android.app.ui.activities.ConferenceSessionListActivity;
import com.sagetech.conference_android.app.ui.activities.EventDetailActivity;
import com.sagetech.conference_android.app.ui.activities.NewEventDetailActivity;
import com.sagetech.conference_android.app.ui.activities.SplashActivity;

import java.lang.reflect.Type;
import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import timber.log.Timber;

@Module(
        injects = {
                ConferenceApplication.class,
                ConferenceListActivity.class,
                ConferenceSessionListActivity.class,
                SplashActivity.class,
                EventDetailActivity.class,
                NewEventDetailActivity.class
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
    ConferenceApi provideConferenceApi() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {

            public void log(String arg0) {
                Timber.i(arg0);
            }

        }).setEndpoint(BuildConfig.API_BASE_URL).setConverter(new GsonConverter(gson)).build();
        return restAdapter.create(ConferenceApi.class);
    }

    @Provides
    @Singleton
    ConferenceController provideConferenceController(ConferenceApi conferenceApi) {
        return new ConferenceController(conferenceApi);
    }

}
