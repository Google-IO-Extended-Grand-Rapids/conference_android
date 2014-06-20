package com.example.conference_android.app.api;

import com.example.conference_android.app.model.EventData;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;

public class ConferenceController {

    private List<EventData> eventData;

    private interface ApiManagerService {
        @GET("/events.json")
        List<EventData> getEvents();
    }

    public List<EventData> getEvents() {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://conference-schedule-webap.herokuapp.com")
                .build();

        final ApiManagerService apiManager = restAdapter.create(ApiManagerService.class);
        final List<EventData> eventData = apiManager.getEvents();

        Collections.sort(eventData, new Comparator<EventData>() {
            public int compare(EventData e1, EventData e2) {
                return e1.getStart_dttm().compareTo(e2.getStart_dttm());
            }
        });

        return eventData;
    }

    public EventData getEvent(Integer eventId) {

        for (EventData e : getEventData()) {
            if (e.getId() == eventId)
                return e;
        }

        return null;
    }

    public List<EventData> getEventData() {
        return eventData;
    }

    public void setEventData(List<EventData> eventData) {
        this.eventData = eventData;
    }

}
