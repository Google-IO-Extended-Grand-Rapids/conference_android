package com.sagetech.conference_android.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.model.EventData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends InjectableActionBarActivity {

    private static final String TAG = "SplashActivity";
    private Subscription subscription;

    @Inject
    Observable<List<EventData>> cachedGetEventsObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        subscription = AppObservable.bindActivity(this, cachedGetEventsObservable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Subscriber<List<EventData>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                        Intent intent = new Intent(getApplicationContext(), ConferenceListActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError", e);
                    }

                    @Override
                    public void onNext(List<EventData> s) {
                        Log.i(TAG, "On Next");
                    }
                });
        Log.i(TAG, "done!!!");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }
}
