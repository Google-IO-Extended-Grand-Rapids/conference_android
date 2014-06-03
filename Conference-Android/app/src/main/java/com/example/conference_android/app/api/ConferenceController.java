package com.example.conference_android.app.api;

import com.example.conference_android.app.model.Conference;
import com.example.conference_android.app.model.EventData;
import com.example.conference_android.app.model.EventLeader;
import com.example.conference_android.app.model.Room;
import com.example.conference_android.app.util.ConferenceFactory;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by carlushenry on 5/28/14.
 */
public class ConferenceController {

    private final Conference conference;

    private interface ApiManagerService {
        @GET("/events.json")
        List<EventData> getEvents();
    }

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


    public List<EventData> getEvents() {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.ioextendedgr.com")
                .build();

        final ApiManagerService apiManager = restAdapter.create(ApiManagerService.class);
        final List<EventData> eventData = apiManager.getEvents();

        return eventData;
    }

    public Conference getConference() {
        return conference;
    }



}
