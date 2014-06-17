package com.example.conference_android.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.example.conference_android.app.ConferenceApplication;
import com.example.conference_android.app.R;
import com.example.conference_android.app.api.ConferenceController;

/**
 * Created by danmikita on 6/15/14.
 */
public class SplashActivity extends ActionBarActivity {

    private ConferenceController conferenceController;

    private Button continueBtn;
    private Button signInBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.conferenceController = ((ConferenceApplication) getApplication()).getConferenceController();

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        continueBtn = (Button) findViewById(R.id.button3);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        signInBtn = (Button) findViewById(R.id.button);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
            }
        });

    }

}
