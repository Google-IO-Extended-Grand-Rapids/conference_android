package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceDataViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by jrobertson on 2/21/15.
 */
public class ConferenceListActivityPresenter implements IConferenceListPresenter {

    private final ConferenceBuilder conferenceBuilder;
    private ConferenceController conferenceController;
    private Subscription subscription;
    private IConferenceListActivity conferenceListActivity;

    public ConferenceListActivityPresenter(ConferenceController conferenceController, IConferenceListActivity activity) {
        this.conferenceController = conferenceController;
        this.conferenceListActivity = activity;
        this.conferenceBuilder = new ConferenceBuilder();
    }

    public void initialize() {

        Observable<List<ConferenceData>> conferenceDataObservable = conferenceController.getConferencesData();

        subscription = conferenceDataObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(populateConferencesSubscriber());

    }

    private Subscriber<List<ConferenceData>> populateConferencesSubscriber() {
        return new Subscriber<List<ConferenceData>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Timber.d("Error occurred...");

            }

            @Override
            public void onNext(List<ConferenceData> conferenceDatas) {

                conferenceListActivity.populateConferences(conferenceBuilder.toConferenceDataViewModel(conferenceDatas));
            }
        };
    }

    public void onDestroy() {
        subscription.unsubscribe();
    }
}
