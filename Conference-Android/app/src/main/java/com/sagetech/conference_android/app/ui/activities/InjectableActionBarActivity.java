package com.sagetech.conference_android.app.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.sagetech.conference_android.app.ConferenceApplication;
import com.sagetech.conference_android.app.R;

/**
 * Created by carlushenry on 2/12/15.
 */
public class InjectableActionBarActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConferenceApplication app = ConferenceApplication.get(this);
        app.inject(this);
    }
}
