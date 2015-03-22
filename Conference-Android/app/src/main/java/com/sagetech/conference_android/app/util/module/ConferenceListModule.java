package com.sagetech.conference_android.app.util.module;


import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.ui.activities.ConferenceListActivity;
import com.sagetech.conference_android.app.ui.presenter.ConferenceListActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceListActivity;
import com.sagetech.conference_android.app.ui.presenter.IConferenceListPresenter;
import com.sagetech.conference_android.app.util.ConferenceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = ConferenceListActivity.class,
        addsTo = ConferenceModule.class
)
public class ConferenceListModule {

    private IConferenceListActivity view;

    public ConferenceListModule(IConferenceListActivity view) {
        this.view = view;
    }

    @Provides @Singleton public IConferenceListActivity provideView() {
        return view;
    }

    @Provides
    @Singleton
    public IConferenceListPresenter conferenceListActivityPresenter(ConferenceController controller, IConferenceListActivity view) {
        return new ConferenceListActivityPresenter(controller, view);
    }
}
