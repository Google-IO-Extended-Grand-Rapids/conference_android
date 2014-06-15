package com.example.conference_android.app;

import android.app.Application;

import com.example.conference_android.app.api.ConferenceController;

/**
 * Created by carlushenry on 5/28/14.
 */
public class ConferenceApplication extends Application {
    private ConferenceController conferenceController;

    @Override
    public void onCreate() {
        super.onCreate();
        this.conferenceController = new ConferenceController();
    }

    public ConferenceController getConferenceController() {
        return conferenceController;
    }
}
