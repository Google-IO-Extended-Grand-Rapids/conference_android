package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.model.ConferenceData;

import java.util.List;

/**
 * Created by jrobertson on 2/21/15.
 */
public interface IConferenceListActivity {
    public void populateConferences(List<ConferenceData> datas);
}
