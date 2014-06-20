package com.example.conference_android.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.conference_android.app.ConferenceApplication;
import com.example.conference_android.app.R;
import com.example.conference_android.app.api.ConferenceController;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SignInActivity extends ActionBarActivity {

    private ConferenceController conferenceController;
    private Subscription subscription;

    private Button signInBtn;
    private EditText usernameTxt;
    private EditText passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        conferenceController = ((ConferenceApplication) getApplication()).getConferenceController();

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        usernameTxt = (EditText) findViewById(R.id.username);
        passwordTxt = (EditText) findViewById(R.id.password);
        signInBtn = (Button) findViewById(R.id.button);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                subscription = Observable.create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        try {
                            boolean success = conferenceController.login(usernameTxt.getText().toString(), passwordTxt.getText().toString());
                            subscriber.onNext(success);
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean o) {
                                if(o) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });

            }
        });



    }




}
