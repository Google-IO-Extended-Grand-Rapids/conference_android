package com.example.conference_android.app.ui.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.conference_android.app.ConferenceApplication;
import com.example.conference_android.app.R;
import com.example.conference_android.app.api.ConferenceController;
import com.example.conference_android.app.model.EventData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class EventDetailActivity extends ActionBarActivity {
    private static final String TAG = "EventDetailActivity";
    private TextView title;
    private TextView location;
    private TextView description;
    private TextView name1;
    private TextView name2;
    private TextView bio1;
    private TextView bio2;
    private ConferenceController conferenceController;
    private Subscription subscription;
    private Integer eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);

        eventId = getIntent().getExtras().getInt("id");

        this.conferenceController = ((ConferenceApplication) getApplication()).getConferenceController();

        this.title = (TextView) findViewById(R.id.title);
        this.location = (TextView) findViewById(R.id.location);
        this.description = (TextView) findViewById(R.id.description);
        this.name1 = (TextView) findViewById(R.id.name1);
        this.name2 = (TextView) findViewById(R.id.name2);
        this.bio1 = (TextView) findViewById(R.id.bio1);
        this.bio2 = (TextView) findViewById(R.id.bio2);


        subscription = Observable.create(new Observable.OnSubscribe<EventData>() {
            @Override
            public void call(Subscriber<? super EventData> subscriber) {
                try {
                    Log.i(TAG, "Looking up first event leader");
                    EventData events = conferenceController.getEvent(eventId);
                    subscriber.onNext(events);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<EventData>() {
                    @Override
                    public void call(EventData o) {
                        Log.i(TAG, "Updating Screen");
                        updateScreen(o);
                    }
                });
        Log.i(TAG, "Completed the onCreate() method");
    }

    private void updateScreen(EventData eventData) {
        String name = eventData.getEvent_leaders().get(0).getFirst_name() + " " + eventData.getEvent_leaders().get(0).getLast_name();


        this.title.setText(eventData.getEvent().getTitle());
        String roomName = "";
        if(eventData.getRoom() != null)
            roomName = eventData.getRoom().getName();
        this.location.setText(formatLocation(eventData.getStart_dttm(), eventData.getEnd_dttm(), roomName));
        this.description.setText(eventData.getEvent().getDescription());
        this.name1.setText(name);
        this.bio1.setText(eventData.getEvent_leaders().get(0).getBiography());
    }

    private String formatLocation(String startString, String endString, String room) {
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(startString);
            endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(endString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat timeFormatter1 = new SimpleDateFormat("EEE, h:mm-", Locale.US);
        SimpleDateFormat timeFormatter2 = new SimpleDateFormat("h:mm aaa", Locale.US);

        return timeFormatter1.format(startDate) + timeFormatter2.format(endDate) + " in " + room;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
