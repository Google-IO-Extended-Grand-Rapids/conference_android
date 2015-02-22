package com.sagetech.conference_android.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
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

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        actionBar.setDisplayHomeAsUpEnabled(true);

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

        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public ViewHolder(View v) {
                super(v);
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
            holder.itemView.setTag(conferenceSessionDatas.get(position).getId());

            TextView day = getTextViewById(holder, R.id.day);
            day.setText(toDay(conferenceSessionDatas.get(position).getStartDttm()));

            TextView time = getTextViewById(holder, R.id.time);
            time.setText(toTime(conferenceSessionDatas.get(position).getStartDttm()));

            TextView title = getTextViewById(holder, R.id.title);
            title.setText(conferenceSessionDatas.get(position).getName());

            TextView room = getTextViewById(holder, R.id.room);
            room.setText("112E"); //TODO -- set to real value once roomdata is available
        }

        private TextView getTextViewById(ConferenceSessionsAdapter.ViewHolder holder, int id) {
            return (TextView) holder.itemView.findViewById(id);
        }

        private String toDay(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT, Locale.US);
            return sdf.format(date);
        }

        @Override
        public int getItemCount() {
            return conferenceSessionDatas.size();
        }

        private String toTime(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
            return sdf.format(date);
        }
    }
}
