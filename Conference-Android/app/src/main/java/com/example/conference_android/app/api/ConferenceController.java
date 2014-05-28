package com.example.conference_android.app.api;

import com.example.conference_android.app.model.Conference;
import com.example.conference_android.app.model.Event;
import com.example.conference_android.app.model.EventLeader;
import com.example.conference_android.app.model.Room;
import com.example.conference_android.app.util.ConferenceFactory;

import java.util.List;

/**
 * Created by carlushenry on 5/28/14.
 */
public class ConferenceController {

    private final Conference conference;

    public ConferenceController() {
        this.conference = ConferenceFactory.createConference();
    }

    public List<Room> getRooms() {
        return ConferenceFactory.getRooms();
    }

    public List<EventLeader> getEventLeaders() {
        return ConferenceFactory.getEventLeaders();
    }

    public EventLeader getFirstEventLeaders() {
        return ConferenceFactory.getEventLeaders().get(0);
    }

    public List<Event> getEvents() {
        return ConferenceFactory.getEvents();
    }

    public Conference getConference() {
        return conference;
    }



}
