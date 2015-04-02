package com.sagetech.conference_android.app.util.module;


import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.ui.activities.ConferenceDetailActivity;
import com.sagetech.conference_android.app.ui.activities.ConferenceListActivity;
import com.sagetech.conference_android.app.ui.presenter.ConferenceDetailActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.ConferenceListActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceDetailActivity;
import com.sagetech.conference_android.app.ui.presenter.IConferenceDetailActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceListActivity;
import com.sagetech.conference_android.app.ui.presenter.IConferenceListPresenter;
import com.sagetech.conference_android.app.util.ConferenceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = ConferenceDetailActivity.class,
        addsTo = ConferenceModule.class
)
public class ConferenceDetailModule {

    private IConferenceDetailActivity view;

    public ConferenceDetailModule(IConferenceDetailActivity view) {
        this.view = view;
    }

    @Provides @Singleton public IConferenceDetailActivity provideView() {
        return view;
    }

    @Provides
    @Singleton
    public IConferenceDetailActivityPresenter provideConferenceListActivityPresenter(ConferenceController controller, IConferenceDetailActivity view) {
        return new ConferenceDetailActivityPresenter(controller, view);
    }
}
