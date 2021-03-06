package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;

import java.util.List;

/**
 * Created by adam on 2/21/15.
 */
public interface IConferenceSessionActivity {
    public void populateConferenceSessions(List<ConferenceSessionViewModel> data);
}
