package com.sagetech.conference_android.app.ui.presenter;

/**
 * Created by carlushenry on 3/22/15.
 */
public interface IConferenceSessionListPresenter {
    void initialize(Integer conferenceId);

    void onDestroy();
}
