package com.example.conference_android.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.conference_android.app.model.EventLeader;

import java.util.List;


public class EventLeaderActivity extends Activity {

    private ConferenceApplication app;
    private TextView txtBiographyValue;
    private TextView txtNameValue;
    private List<EventLeader> eventLeaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventleader);

        this.app = (ConferenceApplication) getApplication();

        this.txtBiographyValue = (TextView) findViewById(R.id.txtBiographyValue);
        this.txtNameValue = (TextView) findViewById(R.id.txtNameValue);
        eventLeaders = app.getEventLeaders();

        EventLeader currEventLeader = eventLeaders.get(0);

        updateScreen(currEventLeader);
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
