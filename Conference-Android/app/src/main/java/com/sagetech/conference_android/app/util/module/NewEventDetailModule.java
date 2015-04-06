package com.sagetech.conference_android.app.util.module;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.ui.activities.NewEventDetailActivity;
import com.sagetech.conference_android.app.ui.presenter.EventDetailActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IEventDetailActivity;
import com.sagetech.conference_android.app.ui.presenter.IEventDetailPresenter;
import com.sagetech.conference_android.app.util.ConferenceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jrobertson on 4/4/15.
 */
@Module(
        injects = NewEventDetailActivity.class,
        addsTo = ConferenceModule.class
)
public class NewEventDetailModule {

    private IEventDetailActivity view;

    public NewEventDetailModule(IEventDetailActivity view) {
        this.view = view;
    }

    @Provides @Singleton public IEventDetailActivity provideView() {
        return view;
    }

    @Provides
    @Singleton
    public IEventDetailPresenter provideEventDetailPresenter(ConferenceController controller, IEventDetailActivity view) {
        return new EventDetailActivityPresenter(view, controller);
    }
}
