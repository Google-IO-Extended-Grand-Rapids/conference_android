package com.sagetech.conference_android.app.api;

import com.sagetech.conference_android.app.model.ConferenceData;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.PresenterData;
import com.sagetech.conference_android.app.model.RoomData;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by carlushenry on 2/19/15.
 */
public interface ConferenceApi {
    @GET("/api/conference")
    Observable<List<ConferenceData>> getConferenceData();

    @GET("/api/conferenceSession/{id}")
    Observable<ConferenceSessionData> getConfereneSessionById(@Path("id") Long id);

    @GET("/api/presenter/{id}")
    Observable<PresenterData> getPresenterById(@Path("id") Long id);

    @GET("/api/room/{id}")
    Observable<RoomData> getRoomById(@Path("id") Long id);

    @GET("/api/conference/{id}/conferenceSessions")
    Observable<List<ConferenceSessionData>> getConferenceSessionsById(@Path("id") Long id);
}
