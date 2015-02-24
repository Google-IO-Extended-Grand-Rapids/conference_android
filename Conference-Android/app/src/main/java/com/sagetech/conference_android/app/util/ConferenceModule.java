package com.sagetech.conference_android.app.util;


import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sagetech.conference_android.app.ConferenceApplication;
import com.sagetech.conference_android.app.api.ConferenceApi;
import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.EventData;
import com.sagetech.conference_android.app.ui.activities.ConferenceListActivity;
import com.sagetech.conference_android.app.ui.activities.ConferenceSessionListActivity;
import com.sagetech.conference_android.app.ui.activities.EventDetailActivity;
import com.sagetech.conference_android.app.ui.activities.MainActivity;
import com.sagetech.conference_android.app.ui.activities.SplashActivity;
import com.sagetech.conference_android.app.ui.fragments.FullScheduleFragment;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import rx.Observable;

@Module(
        injects = {
                ConferenceApplication.class,
                ConferenceListActivity.class,
                ConferenceSessionListActivity.class,
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
    ConferenceApi provideConferenceApi() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
                .create();

        String url = "http://104.236.204.59:8080";
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {

            public void log(String arg0) {
                System.out.println(arg0);
            }

        }).setEndpoint(url).setConverter(new GsonConverter(gson)).build();
        return restAdapter.create(ConferenceApi.class);
    }

    @Provides
    @Singleton
    ConferenceController provideConferenceController(ConferenceApi conferenceApi) {
        return new ConferenceController(conferenceApi);
    }

    @Provides
    @Singleton
    Observable<List<EventData>> provideCachedGetEventsObservable(ConferenceController conferenceController) {
        return conferenceController.getEventsObservable().cache();
    }

}
