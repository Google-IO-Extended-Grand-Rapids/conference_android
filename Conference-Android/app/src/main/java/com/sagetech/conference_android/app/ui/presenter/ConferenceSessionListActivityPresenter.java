package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.ui.activities.ConferenceSessionListActivity;

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
                Timber.e(e, "Error in ConferenceSessionListActivityPresenter", (Object)null);

            }

            @Override
            public void onNext(List<ConferenceSessionData> conferenceSessionDatas) {
                conferenceSessionListActivity.populateConferenceSessions(conferenceSessionDatas);
            }
        });
    }

    public void onDestroy() {
        subscription.unsubscribe();
    }
}
