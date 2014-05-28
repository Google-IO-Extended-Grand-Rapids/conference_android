package com.example.conference_android.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.conference_android.app.api.ConferenceController;
import com.example.conference_android.app.model.EventLeader;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class EventLeaderActivity extends Activity {
    private static final String TAG = "EventLeaderActivity";
    private TextView txtBiographyValue;
    private TextView txtNameValue;
    private ConferenceController conferenceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventleader);

        this.conferenceController = ((ConferenceApplication) getApplication()).getConferenceController();

        this.txtBiographyValue = (TextView) findViewById(R.id.txtBiographyValue);
        this.txtNameValue = (TextView) findViewById(R.id.txtNameValue);


        Observable.create(new Observable.OnSubscribe<EventLeader>() {
            @Override
            public void call(Subscriber<? super EventLeader> subscriber) {
                try {
                    Log.i(TAG, "Looking up first event leader");
                    EventLeader currEventLeader = conferenceController.getFirstEventLeaders();
                    subscriber.onNext(currEventLeader);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<EventLeader>() {
            @Override
            public void call(EventLeader o) {
                Log.i(TAG, "Updating Screen");
                updateScreen(o);
            }
        });
        Log.i(TAG, "Completed the onCreate() method");
    }

    private void updateScreen(EventLeader currEventLeader) {
        this.txtNameValue.setText(String.format("%s %s", currEventLeader.getFirstName(), currEventLeader.getLastName()));
        this.txtBiographyValue.setText(currEventLeader.getBiography());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.presenter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
