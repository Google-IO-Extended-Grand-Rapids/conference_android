package com.sagetech.conference_android.app.util.module;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.ui.activities.ConferenceSessionDetailActivity;
import com.sagetech.conference_android.app.ui.presenter.ConferenceSessionDetailActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceSessionDetailActivity;
import com.sagetech.conference_android.app.ui.presenter.IConferenceSessionDetailPresenter;
import com.sagetech.conference_android.app.util.ConferenceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jrobertson on 4/4/15.
 */
@Module(
        injects = ConferenceSessionDetailActivity.class,
        addsTo = ConferenceModule.class
)
public class ConferenceSessionDetailModule {

    private IConferenceSessionDetailActivity view;

    public ConferenceSessionDetailModule(IConferenceSessionDetailActivity view) {
        this.view = view;
    }

    @Provides @Singleton public IConferenceSessionDetailActivity provideView() {
        return view;
    }

    @Provides
    @Singleton
    public IConferenceSessionDetailPresenter provideEventDetailPresenter(ConferenceController controller, IConferenceSessionDetailActivity view) {
        return new ConferenceSessionDetailActivityPresenter(view, controller);
    }
}
