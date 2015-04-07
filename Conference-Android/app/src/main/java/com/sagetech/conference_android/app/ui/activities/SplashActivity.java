package com.sagetech.conference_android.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.sagetech.conference_android.app.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class SplashActivity extends ActionBarActivity {

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        subscription = AppObservable.bindActivity(this, Observable.just(1))
                .delay(3, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Timber.i("onCompleted");
                        Intent intent = new Intent(getApplicationContext(), ConferenceListActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.i("onError", e);
                    }

                    @Override
                    public void onNext(Integer s) {
                        Timber.i("On Next");
                    }
                });
        Timber.i("done!!!");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

}
