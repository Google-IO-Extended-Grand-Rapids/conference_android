package com.example.conference_android.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.example.conference_android.app.ConferenceApplication;
import com.example.conference_android.app.R;
import com.example.conference_android.app.api.ConferenceController;
import com.example.conference_android.app.model.EventData;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SplashActivity extends ActionBarActivity {

    private ConferenceController conferenceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        conferenceController = ((ConferenceApplication) getApplication()).getConferenceController();

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Observable.create(new Observable.OnSubscribe<List<EventData>>() {
            @Override
            public void call(Subscriber<? super List<EventData>> subscriber) {
                try {
                    List<EventData> events = conferenceController.getEvents();
                    subscriber.onNext(events);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<EventData>>() {
                    @Override
                    public void call(List<EventData> o) {
                        conferenceController.setEventData(o);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
