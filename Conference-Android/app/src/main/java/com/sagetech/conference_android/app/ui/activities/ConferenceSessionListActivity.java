package com.sagetech.conference_android.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.ui.listener.RecyclerItemClickListener;
import com.sagetech.conference_android.app.ui.presenter.ConferenceSessionListActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceSessionActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

/**
 * Created by adam on 2/21/15.
 */
public class ConferenceSessionListActivity extends InjectableActionBarActivity implements IConferenceSessionActivity {
    @Inject
    ConferenceController conferenceController;

    @InjectView(R.id.confView)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ConferenceSessionListActivityPresenter presenter = null;
    private Integer conferenceId;

    @Override
    public void populateConferenceSessions(List<ConferenceSessionData> datas) {
        mAdapter = new ConferenceSessionsAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferences);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.inject(this);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Timber.d(String.format("View Clicked: %s", view.getTag()));

                        Intent eventDetailIntent = new Intent(view.getContext(), EventDetailActivity.class);
                        eventDetailIntent.putExtra("id", (Long)view.getTag());
                        startActivity(eventDetailIntent);
                    }
                })
        );

        conferenceId = getIntent().getExtras().getInt("id");

        presenter = new ConferenceSessionListActivityPresenter(this, conferenceController, Long.valueOf(conferenceId));
        presenter.initialize();
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public class ConferenceSessionsAdapter extends RecyclerView.Adapter<ConferenceSessionsAdapter.ViewHolder> {
        private final List<ConferenceSessionData> conferenceSessionDatas;
        private final String TIME_FORMAT = "h:mm a";
        private final String DAY_FORMAT = "EEEE, MMMM dd, yyyy";
        private final SimpleDateFormat DAY_FORMATTER = new SimpleDateFormat(DAY_FORMAT, Locale.US);
        private final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat(TIME_FORMAT);

        public class ViewHolder extends RecyclerView.ViewHolder {
            @InjectView(R.id.day) public TextView dayView;
            @InjectView(R.id.time) public TextView timeView;
            @InjectView(R.id.title) public TextView titleView;
            @InjectView(R.id.room) public TextView roomView;

            // each data item is just a string in this case
            public ViewHolder(View v) {
                super(v);
                ButterKnife.inject(this, v);
            }

            public void setTag(Object tag) {
                this.itemView.setTag(tag);
            }

            public void setTitle(final String title) {
                this.titleView.setText(title);
            }

            public void setDay(final Date date) {
                this.dayView.setText(DAY_FORMATTER.format(date));
            }

            public void setTime(final Date time) {
                this.timeView.setText(TIME_FORMATTER.format(time));
            }

            public void setRoom(final String room) {
                this.roomView.setText(room);
            }
        }

        public ConferenceSessionsAdapter(List<ConferenceSessionData> conferenceSessionDatas) {
            this.conferenceSessionDatas = conferenceSessionDatas;
        }

        @Override
        public ConferenceSessionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_view_item, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ConferenceSessionsAdapter.ViewHolder holder, int position) {
            holder.setTag(conferenceSessionDatas.get(position).getId());
            holder.setDay(conferenceSessionDatas.get(position).getStartDttm());
            holder.setTime(conferenceSessionDatas.get(position).getStartDttm());
            holder.setTitle(conferenceSessionDatas.get(position).getName());
            holder.setRoom("112E"); //TODO -- set to real value once roomdata is available
        }

        private String toDay(Date date) {
            return DAY_FORMATTER.format(date);
        }

        @Override
        public int getItemCount() {
            return conferenceSessionDatas.size();
        }

        private String toTime(Date date) {
            return TIME_FORMATTER.format(date);
        }
    }
}
