package com.sagetech.conference_android.app.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.ui.adapters.SessionPresenterAdapter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceSessionDetailActivity;
import com.sagetech.conference_android.app.ui.presenter.IConferenceSessionDetailPresenter;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionDetailViewModel;
import com.sagetech.conference_android.app.util.module.ConferenceSessionDetailModule;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

/**
 * Created by carlushenry on 3/15/15.
 */
public class ConferenceSessionDetailActivity extends InjectableActionBarActivity implements IConferenceSessionDetailActivity {

    @Inject
    IConferenceSessionDetailPresenter presenter;

    @InjectView(R.id.txtTitle)
    TextView title;

    @InjectView(R.id.txtSchedule)
    TextView schedule;

    @InjectView(R.id.txtRoom)
    TextView room;

    @InjectView(R.id.imgSessionType)
    ImageView sessionType;

    @InjectView(R.id.txtDescription)
    TextView description;

    @InjectView(R.id.presenterView)
    RecyclerView mPresenterView;

    private SessionPresenterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_session_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Timber.d("onCreate");

        ButterKnife.inject(this);

        mPresenterView.setHasFixedSize(true);
        mPresenterView.setLayoutManager(new LinearLayoutManager(this));

        super.setTitle("Session Details");

        Long eventId = getIntent().getLongExtra("id", 0);
        presenter.initialize(eventId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void populateWithConferenceSessionDetailView(ConferenceSessionDetailViewModel eventDetailViewModel) {
        setTitle(eventDetailViewModel.getTitle());
        setSchedule(eventDetailViewModel.getEventDateAndDuration());
        setRoom(eventDetailViewModel.getRoomName());
        setDescription(eventDetailViewModel.getDescription());
        mAdapter = new SessionPresenterAdapter(eventDetailViewModel.getPresenters());
        mPresenterView.setAdapter(mAdapter);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ConferenceSessionDetailModule(this));
    }

    private void setTitle(String title) {
        this.title.setText(title);
    }

    private void setSchedule(String schedule) {
        this.schedule.setText(schedule);
    }

    private void setRoom(String room) {
        this.room.setText(room);
    }

    private void setSessionTypeImg(String imageUrl) {

    }

    private void setDescription(String description) {
        this.description.setText(description);
    }

}
