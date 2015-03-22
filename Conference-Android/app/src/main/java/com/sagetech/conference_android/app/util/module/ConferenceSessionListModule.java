package com.sagetech.conference_android.app.util.module;


import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.ui.activities.ConferenceListActivity;
import com.sagetech.conference_android.app.ui.activities.ConferenceSessionListActivity;
import com.sagetech.conference_android.app.ui.presenter.ConferenceListActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.ConferenceSessionListActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceListActivity;
import com.sagetech.conference_android.app.ui.presenter.IConferenceSessionActivity;
import com.sagetech.conference_android.app.ui.presenter.IConferenceSessionListPresenter;
import com.sagetech.conference_android.app.util.ConferenceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = ConferenceSessionListActivity.class,
        addsTo = ConferenceModule.class
)
public class ConferenceSessionListModule {

    private IConferenceSessionActivity view;

    public ConferenceSessionListModule(IConferenceSessionActivity view) {
        this.view = view;
    }

    @Provides @Singleton public IConferenceSessionActivity provideView() {
        return view;
    }

    @Provides
    @Singleton
    public IConferenceSessionListPresenter provideConferenceListActivityPresenter(ConferenceController controller, IConferenceSessionActivity view) {
        return new ConferenceSessionListActivityPresenter(view, controller);
    }
}
