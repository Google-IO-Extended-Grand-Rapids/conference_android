package com.sagetech.conference_android.app.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.ui.adapters.SessionPresenterAdapter;
import com.sagetech.conference_android.app.ui.presenter.IEventDetailActivity;
import com.sagetech.conference_android.app.ui.presenter.IEventDetailPresenter;
import com.sagetech.conference_android.app.ui.viewModel.EventDetailViewModel;
import com.sagetech.conference_android.app.util.module.NewEventDetailModule;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

/**
 * Created by carlushenry on 3/15/15.
 */
public class NewEventDetailActivity extends InjectableActionBarActivity implements IEventDetailActivity {

    @Inject
    IEventDetailPresenter presenter;

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

    @InjectView(R.id.txtRecommend)
    TextView recommend;

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

        Long eventId = getIntent().getLongExtra("id", 0);
        presenter.initialize(eventId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void populateWithEventDetailView(EventDetailViewModel eventDetailViewModel) {
        setTitle(eventDetailViewModel.getTitle());
        setSchedule(eventDetailViewModel.getEventDateAndDuration());
        setRoom(eventDetailViewModel.getRoomName());
        setDescription(eventDetailViewModel.getDescription());
        setRecommend("+437 Recommended this on Google"); //TODO: set recommend stats when available
        mAdapter = new SessionPresenterAdapter(eventDetailViewModel.getPresenters());
        mPresenterView.setAdapter(mAdapter);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new NewEventDetailModule(this));
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

    private void setRecommend(String recommend) {
        this.recommend.setText(recommend);
    }
}
