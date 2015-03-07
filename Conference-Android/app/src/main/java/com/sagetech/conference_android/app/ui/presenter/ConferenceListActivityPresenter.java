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
public class ConferenceListActivityPresenter {

    private ConferenceController conferenceController;
    private Subscription subscription;
    private IConferenceListActivity conferenceListActivity;

    public ConferenceListActivityPresenter(ConferenceController conferenceController, IConferenceListActivity activity) {
        this.conferenceController = conferenceController;
        this.conferenceListActivity = activity;
    }

    public void initialize() {

        Observable<List<ConferenceData>> conferenceDataObservable = conferenceController.getConferencesData();

        subscription = conferenceDataObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<ConferenceData>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Timber.d("Error occurred...");

            }

            @Override
            public void onNext(List<ConferenceData> conferenceDatas) {

                conferenceListActivity.populateConferences(buildViewModel(conferenceDatas));
            }
        });

    }

    public List<ConferenceDataViewModel> buildViewModel(List<ConferenceData> conferenceDatas) {
        List<ConferenceDataViewModel> confDataViewModels = new ArrayList<ConferenceDataViewModel>();

        for (ConferenceData currConfData : conferenceDatas) {
            confDataViewModels.add(toConferenceDataViewModel(currConfData));
        }

        return confDataViewModels;
    }

    private ConferenceDataViewModel toConferenceDataViewModel(ConferenceData currConfData) {
        ConferenceDataViewModel confDataViewModel = new ConferenceDataViewModel();

        //TODO Fix this and get the city and state from the location.
        confDataViewModel.setCityAndState("Grand Rapids, MI");
        confDataViewModel.setName(currConfData.getName());
        confDataViewModel.setId(currConfData.getId());

        return confDataViewModel;
    }
}
