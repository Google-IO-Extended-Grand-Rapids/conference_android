package com.sagetech.conference_android.app.ui.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.ui.presenter.IConferenceDetailActivity;
import com.sagetech.conference_android.app.ui.presenter.IConferenceDetailActivityPresenter;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceDetailViewModel;
import com.sagetech.conference_android.app.util.module.ConferenceDetailModule;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ConferenceDetailActivity extends InjectableActivity implements IConferenceDetailActivity {


    @InjectView(R.id.txtConferenceName)
    TextView txtConferenceName;

    @InjectView(R.id.conferenceImageView)
    ImageView imgConferenceImageView;

    @InjectView(R.id.txtConferenceDate)
    TextView txtConferenceDate;

    @InjectView(R.id.txtConferenceFullDesc)
    TextView txtConferenceFullDesc;

    @InjectView(R.id.txtContactText)
    TextView txtContactText;

    @Inject
    IConferenceDetailActivityPresenter presenter;

    ImageView imgConfMap;

    private Long conferenceId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conference_detail);
        ButterKnife.inject(this);

        conferenceId = getIntent().getLongExtra("id", 0);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_conference_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgConfMap = (ImageView) getSupportActionBar().getCustomView().findViewById(R.id.imgConfDetailMap);
        imgConfMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conferenceSessionListIntent = new Intent(imgConfMap.getContext(), ConferenceSessionListActivity.class);
                conferenceSessionListIntent.putExtra("id", conferenceId);
                startActivity(conferenceSessionListIntent);
            }
        });

        // yes I am being lazy...
        presenter.initialize(conferenceId);

    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ConferenceDetailModule(this));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void populateConferenceData(ConferenceDetailViewModel conferenceDetailViewModel) {
        // load the conference image
        Picasso.with(this)
                .load(conferenceDetailViewModel.getImageUrl())
                .placeholder(R.drawable.default_event)
                .error(R.drawable.default_event)
                .into(imgConferenceImageView);

        txtConferenceName.setText(conferenceDetailViewModel.getName());
        txtConferenceDate.setText(conferenceDetailViewModel.getDateInformation());
        txtConferenceFullDesc.setText(conferenceDetailViewModel.getFullDescription());
        txtContactText.setText(conferenceDetailViewModel.getConferenceContactPerson());
    }
}
