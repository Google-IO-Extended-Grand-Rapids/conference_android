package com.example.conference_android.app.api;

import com.example.conference_android.app.model.EventData;
import com.squareup.okhttp.OkHttpClient;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import retrofit.RestAdapter;
import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by carlushenry on 5/28/14.
 */
public class ConferenceController {

    private List<EventData> eventData;
    private static String cookie;

    private interface ApiManagerService {
        @GET("/events.json")
        List<EventData> getEvents(@retrofit.http.Header("Cookie") String authorization);

        @GET("/events/{event_id}.json")
        EventData getEvent(@Path("event_id") Integer eventId);

        @FormUrlEncoded
        @POST("/login")
        Response login(@Field("username") String username, @Field("password") String password);
    }

    public List<EventData> getEvents() {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.HEADERS)
                .setEndpoint("https://conference-schedule-webap.herokuapp.com")
                .build();

        final ApiManagerService apiManager = restAdapter.create(ApiManagerService.class);
        final List<EventData> eventData = apiManager.getEvents(cookie);

        Collections.sort(eventData, new Comparator<EventData>() {
            public int compare(EventData e1, EventData e2) {
                return e1.getStart_dttm().compareTo(e2.getStart_dttm());
            }
        });

        this.eventData = eventData;

        return eventData;
    }

    public List<EventData> getEventData() {

        if (this.eventData == null) {
            this.eventData = getEvents();
        }

        return this.eventData;
    }

    public List<EventData> getScheduledEvents() {

        if (getEventData() == null)
            return getScheduledEvents();

        final List<EventData> eventData = getEventData();
        ListIterator iterator = eventData.listIterator();
        while (iterator.hasNext()) {
            if (((EventData) iterator.next()).getChosen_by_attendee() == false) {
                iterator.remove();
            }
        }

        return eventData;
    }

    public EventData getEvent(Integer eventId) {
        ListIterator iterator = getEventData().listIterator();
        while (iterator.hasNext()) {
            EventData event = (EventData) iterator.next();
            if (event.getId() == eventId) return event;
        }
        return null;
    }

    public void login(String username, String password) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://conference-schedule-webap.herokuapp.com")
                .setClient(new OkClient(new OkHttpClient()))
                .build();

        final ApiManagerService apiManager = restAdapter.create(ApiManagerService.class);
        final Response response = apiManager.login(username, password);


        ListIterator iterator = response.getHeaders().listIterator();
        while (iterator.hasNext()) {
            Header header = (Header) iterator.next();
            if (header.getName() != null && header.getName().equalsIgnoreCase("Set-Cookie"))
                if (header.getValue().contains("session")) {
                    cookie = header.getValue();
                    break;
                }
        }

    }
}
