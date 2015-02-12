package com.sagetech.conference_android.app.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.sagetech.conference_android.app.ConferenceApplication;

/**
 * Created by carlushenry on 2/12/15.
 */
public class InjectibleListFragment extends ListFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConferenceApplication app = ConferenceApplication.get(this.getActivity());
        app.inject(this);
    }
}
