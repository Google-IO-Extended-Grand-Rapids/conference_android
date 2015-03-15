package com.sagetech.conference_android.app.ui.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ConferenceDetailActivity extends ActionBarActivity {


    @InjectView(R.id.txtConferenceName)
    TextView txtConferenceName;
    private int conferenceId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conference_detail);
        ButterKnife.inject(this);

        conferenceId = getIntent().getIntExtra("id", 0);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_conference_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO This is a hack to bring up the conference session list.
        txtConferenceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent conferenceSessionListIntent = new Intent(v.getContext(), ConferenceSessionListActivity.class);
                conferenceSessionListIntent.putExtra("id", conferenceId);
                startActivity(conferenceSessionListIntent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_conference_detail, menu);
        return true;
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
