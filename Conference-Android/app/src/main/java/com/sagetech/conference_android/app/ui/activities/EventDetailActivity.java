package com.sagetech.conference_android.app.ui.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.ui.presenter.EventDetailActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IEventDetailActivity;
import com.sagetech.conference_android.app.ui.viewModel.EventDetailView;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;


public class EventDetailActivity extends InjectableActionBarActivity implements IEventDetailActivity {
    @Inject
    ConferenceController conferenceController;

    @InjectView(R.id.title)
    TextView title;

    @InjectView(R.id.location)
    TextView location;

    @InjectView(R.id.description)
    TextView description;

    @InjectView(R.id.name1)
    TextView name1;

    @InjectView(R.id.name2)
    TextView name2;

    @InjectView(R.id.bio1)
    TextView bio1;

    @InjectView(R.id.bio2)
    TextView bio2;

    @InjectView(R.id.scheduled)
    ImageView img;

    private Integer eventId;

    EventDetailActivityPresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ButterKnife.inject(this);
        Timber.d("onCreate");

        eventId = getIntent().getExtras().getInt("id");
        presenter = new EventDetailActivityPresenter(this, conferenceController, eventId);
        presenter.initialize();
    }

    @Override
    public void populateWithEventDetailView(EventDetailView eventDetailView) {
        populatePresenters(eventDetailView.getPresenters());
        populateEventInfo(eventDetailView);

    }

    private void populatePresenters(List<EventDetailView.EventDetailPresenterView> presenters) {
        if (presenters == null || presenters.isEmpty()) {
            return;
        }
        // at most, we can only have two presenters.  Since we are not using a list view
        // we have to explicitly set the values
        for (int i=0; i < presenters.size(); i++) {
            EventDetailView.EventDetailPresenterView currPresenter = presenters.get(i);
            TextView bio = (i == 0 ? bio1 : bio2);
            TextView name = (i == 0 ? name1 : name2);

            bio.setText(currPresenter.getBiography());
            name.setText(currPresenter.getFullName());
        }
    }


    private void populateEventInfo(EventDetailView eventDetailView) {
        this.title.setText(eventDetailView.getTitle());
        this.location.setText(eventDetailView.getLocationAndStartTime());
        this.description.setText(eventDetailView.getDescription());

        if (EventDetailView.EventType.PRESENTATION == eventDetailView.getEventType()) {
            img.setImageResource(R.drawable.presentation_icon);
        } else if (EventDetailView.EventType.CODE_LABS == eventDetailView.getEventType()) {
            img.setImageResource(R.drawable.codelabs_icon);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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

}
