package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.ui.activities.ConferenceSessionListActivity;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by adam on 2/21/15.
 */
public class ConferenceSessionListActivityPresenter {
    private IConferenceSessionActivity conferenceSessionListActivity;
    private ConferenceController conferenceController;
    private Integer conferenceId;
    private Subscription subscription;

    public ConferenceSessionListActivityPresenter(IConferenceSessionActivity conferenceSessionListActivity, ConferenceController conferenceController, Integer conferenceId) {
        this.conferenceSessionListActivity = conferenceSessionListActivity;
        this.conferenceController = conferenceController;
        this.conferenceId = conferenceId;
    }

    public void initialize() {
        Observable<List<ConferenceSessionData>> conferenceDataObservable = conferenceController.getConferenceSessionsById(conferenceId).cache();

        subscription = conferenceDataObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<ConferenceSessionData>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "Error in ConferenceSessionListActivityPresenter", null);

            }

            @Override
            public void onNext(List<ConferenceSessionData> conferenceSessionDatas) {
                conferenceSessionListActivity.populateConferenceSessions(toConferenceSessionViewModel(conferenceSessionDatas));
            }

        });
    }

    private List<ConferenceSessionViewModel> toConferenceSessionViewModel(List<ConferenceSessionData> conferenceSessionDatas) {

        List<ConferenceSessionViewModel> confSessionViewModels = new ArrayList<>();
        if (conferenceSessionDatas == null) {
            return confSessionViewModels;
        }

        for (ConferenceSessionData currConfSession : conferenceSessionDatas) {
            confSessionViewModels.add(toConfrenceSessionViewModel(currConfSession));
        }
        return confSessionViewModels;
    }

    private ConferenceSessionViewModel toConfrenceSessionViewModel(ConferenceSessionData currConfSession) {
        ConferenceSessionViewModel confViewModel = new ConferenceSessionViewModel();

        confViewModel.setId(currConfSession.getId());
        confViewModel.setRoom("112E");
        confViewModel.setStartDttm(currConfSession.getStartDttm());
        confViewModel.setTitle(currConfSession.getName());

        return confViewModel;
    }

    public void onDestroy() {
        subscription.unsubscribe();
    }

}
