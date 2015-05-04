package com.sagetech.conference_android.app.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sagetech.conference_android.app.ConferenceApplication;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by carlushenry on 2/12/15.
 */
public abstract class InjectableActivity extends AppCompatActivity {

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectThisActivityIfNecessary();
    }

    private void injectThisActivityIfNecessary() {
        List<Object> modules = getModules();
        if (modules == null) {
            return;
        }
        activityGraph = ((ConferenceApplication) getApplication()).createScopedGraph(modules.toArray());
        activityGraph.inject(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    protected abstract List<Object> getModules();
}
