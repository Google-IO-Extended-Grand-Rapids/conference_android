package com.sagetech.conference_android.app.util.module;


import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.ui.activities.ConferenceSessionListActivity;
import com.sagetech.conference_android.app.ui.activities.EventDetailActivity;
import com.sagetech.conference_android.app.ui.presenter.ConferenceSessionListActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.EventDetailActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceSessionActivity;
import com.sagetech.conference_android.app.ui.presenter.IConferenceSessionListPresenter;
import com.sagetech.conference_android.app.ui.presenter.IEventDetailActivity;
import com.sagetech.conference_android.app.ui.presenter.IEventDetailPresenter;
import com.sagetech.conference_android.app.util.ConferenceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = EventDetailActivity.class,
        addsTo = ConferenceModule.class
)
public class EventDetailModule {

    private IEventDetailActivity view;

    public EventDetailModule(IEventDetailActivity view) {
        this.view = view;
    }

    @Provides @Singleton public IEventDetailActivity provideView() {
        return view;
    }

    @Provides
    @Singleton
    public IEventDetailPresenter eventDetailPresenter(ConferenceController controller, IEventDetailActivity view) {
        return new EventDetailActivityPresenter(view, controller);
    }
}
