package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceDetailViewModel;

import rx.Observer;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by carlushenry on 4/2/15.
 */
public class ConferenceDetailActivityPresenter implements IConferenceDetailActivityPresenter {
    private final ConferenceController controller;
    private final IConferenceDetailActivity view;

    public ConferenceDetailActivityPresenter(ConferenceController controller, IConferenceDetailActivity view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void initialize(Long conferenceId) {

        controller.getConferenceDataById(conferenceId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConferenceData>() {
                    ConferenceData conferenceData;
                    @Override
                    public void onCompleted() {
                        view.populateConferenceData(new ConferenceDetailViewModel(conferenceData));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ConferenceData conferenceData) {
                        this.conferenceData = conferenceData;
                    }
                });
    }
}
