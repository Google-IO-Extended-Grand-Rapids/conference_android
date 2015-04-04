package com.sagetech.conference_android.app.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.ui.presenter.EventDetailActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IEventDetailActivity;
import com.sagetech.conference_android.app.ui.viewModel.EventDetailViewModel;

import javax.inject.Inject;

import butterknife.InjectView;
import timber.log.Timber;

/**
 * Created by carlushenry on 3/15/15.
 */
public class NewEventDetailActivity extends ActionBarActivity implements IEventDetailActivity {

    @Inject
    EventDetailActivityPresenter presenter;

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

    @InjectView(R.id.imgPin)
    ImageView pin;

    @InjectView(R.id.txtRecommend)
    TextView recommend;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_session_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Timber.d("onCreate");

        Long eventId = getIntent().getExtras().getLong("id");
        presenter.initialize(eventId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void populateWithEventDetailView(EventDetailViewModel eventDetailViewModel) {


    }
}
