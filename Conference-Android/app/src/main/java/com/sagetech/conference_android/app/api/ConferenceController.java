package com.sagetech.conference_android.app.api;

import com.sagetech.conference_android.app.model.ConferenceData;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.EventData;
import com.sagetech.conference_android.app.model.PresenterData;
import com.sagetech.conference_android.app.model.RoomData;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

public class ConferenceController {

    private final ConferenceApi conferenceApi;

    public ConferenceController(ConferenceApi conferenceApi) {
        this.conferenceApi = conferenceApi;
    }


    public Observable<List<ConferenceData>> getConferencesData() {
        return conferenceApi.getConferenceData();
    }

    public Observable<ConferenceSessionData> getConferenceSessionDataById(Long id) {
        return conferenceApi.getConferenceSessionById(id);
    }

    public Observable<List<ConferenceSessionData>> getConferenceSessionsById(Long conferenceId) {
        return conferenceApi.getConferenceSessionsById(conferenceId);
    }

    public Observable<PresenterData> getPresenterById(Long id) {
        return conferenceApi.getPresenterById(id);
    }

    public Observable<RoomData> getRoomById(Long id) {
        return conferenceApi.getRoomById(id);
    }

}
