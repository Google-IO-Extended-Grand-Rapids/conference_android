package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceSessionData;

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
public class ConferenceSessionListActivityPresenter implements IConferenceSessionListPresenter {
    private IConferenceSessionActivity conferenceSessionListActivity;
    private ConferenceController conferenceController;
    private Subscription subscription;

    public ConferenceSessionListActivityPresenter(IConferenceSessionActivity conferenceSessionListActivity, ConferenceController conferenceController) {
        this.conferenceSessionListActivity = conferenceSessionListActivity;
        this.conferenceController = conferenceController;
    }

    public void initialize(Integer conferenceId) {
        Observable<List<ConferenceSessionData>> conferenceDataObservable = conferenceController.getConferenceSessionsById(conferenceId).cache();

        subscription = conferenceDataObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<ConferenceSessionData>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "Error in ConferenceSessionListActivityPresenter", (Object)null);

            }

            @Override
            public void onNext(List<ConferenceSessionData> conferenceSessionDatas) {
                conferenceSessionListActivity.populateConferenceSessions(ConferenceSessionViewBuilder.toConferenceSessionViewModel(conferenceSessionDatas));
            }

        });
    }

    public void onDestroy() {
        subscription.unsubscribe();
    }

}
