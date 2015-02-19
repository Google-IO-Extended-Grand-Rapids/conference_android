package com.sagetech.conference_android.app.api;

import android.util.Log;

import com.sagetech.conference_android.app.model.ConferenceData;
import com.sagetech.conference_android.app.model.EventData;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;
import rx.Subscriber;

public class ConferenceController {
    private static final String TAG = "ConferenceController";
    private static final RestAdapter apiRestAdapter = new RestAdapter.Builder()
            .setEndpoint("http://104.236.204.59:8080/api")
            .build();

    private List<EventData> eventData;
    private List<ConferenceData> conferenceData;

    private interface  ApiConferenceManagerService {
        @GET("/conference")
        List<ConferenceData> getConferenceData();
    }

    private interface ApiManagerService {
        @GET("/events.json")
        List<EventData> getEvents();
    }


    public Observable<List<EventData>> getEventsObservable() {
        return Observable.create(new Observable.OnSubscribe<List<EventData>>() {
            @Override
            public void call(Subscriber<? super List<EventData>> subscriber) {
                try {
                    subscriber.onNext(getEvents());
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        });
    }

    public List<ConferenceData> getConferences() {
        final ApiConferenceManagerService conferenceApi = apiRestAdapter.create(ApiConferenceManagerService.class);
        final List<ConferenceData> conferenceData = conferenceApi.getConferenceData();

        return conferenceData;
    }

    public List<EventData> getEvents() {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://conference-schedule-webap.herokuapp.com")
                .build();

        Log.i(TAG, "Calling the API...");
        final ApiManagerService apiManager = restAdapter.create(ApiManagerService.class);
        final List<EventData> eventData = apiManager.getEvents();

        Collections.sort(eventData, new Comparator<EventData>() {
            public int compare(EventData e1, EventData e2) {
                return e1.getStart_dttm().compareTo(e2.getStart_dttm());
            }
        });

        for (EventData e : eventData) {
            e.setHeader(false);
        }

        addHeaders(eventData);

        return eventData;
    }

    private void addHeaders(List<EventData> eventData) {

        EventData day1 = new EventData();
        day1.setHeader(true);
        day1.setStart_dttm("Wednesday, June 25, 2014");

        EventData day2 = new EventData();
        day2.setHeader(true);
        day2.setStart_dttm("Thursday, June 26, 2014");

        eventData.add(0, day1);

        for (int i = 1; i < eventData.size(); i++) {
            if (eventData.get(i).getStart_dttm().contains("2014-06-26")) {
                eventData.add(i, day2);
                break;
            }
        }

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
