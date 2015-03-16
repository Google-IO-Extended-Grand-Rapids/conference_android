package com.sagetech.conference_android.app.ui.activities;

import android.os.Bundle;

import com.sagetech.conference_android.app.R;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by carlushenry on 3/15/15.
 */
public class NewEventDetailActivity extends InjectableActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_session_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Timber.d("onCreate");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
