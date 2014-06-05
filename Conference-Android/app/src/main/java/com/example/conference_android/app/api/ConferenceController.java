package com.example.conference_android.app.api;

import com.example.conference_android.app.model.EventData;

import java.util.List;
import java.util.ListIterator;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by carlushenry on 5/28/14.
 */
public class ConferenceController {

    private interface ApiManagerService {
        @GET("/events.json")
        List<EventData> getEvents();

        @GET("/events/{event_id}.json")
        EventData getEvent(@Path("event_id") Integer eventId);
    }

    public List<EventData> getEvents() {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.ioextendedgr.com")
                .build();

        final ApiManagerService apiManager = restAdapter.create(ApiManagerService.class);
        final List<EventData> eventData = apiManager.getEvents();

        return eventData;
    }

    public List<EventData> getScheduledEvents() {
        final List<EventData> eventData = getEvents();

        ListIterator iterator = eventData.listIterator();
        while (iterator.hasNext()) {
            if (((EventData) iterator.next()).getChosen_by_attendee().equals("false")) {
                iterator.remove();
            }
        }

        return eventData;
    }

    public EventData getEvent(Integer eventId) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.ioextendedgr.com")
                .build();

        final ApiManagerService apiManager = restAdapter.create(ApiManagerService.class);
        final EventData eventData = apiManager.getEvent(eventId);

        return eventData;
    }
}
