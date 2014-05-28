package com.example.conference_android.app;

import android.app.Application;

import com.example.conference_android.app.api.ConferenceController;
import com.example.conference_android.app.model.Conference;
import com.example.conference_android.app.model.Event;
import com.example.conference_android.app.model.EventLeader;
import com.example.conference_android.app.model.Room;
import com.example.conference_android.app.util.ConferenceFactory;

import java.util.List;

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
