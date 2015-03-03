package com.sagetech.conference_android.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceData;
import com.sagetech.conference_android.app.ui.presenter.ConferenceListActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceListActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

public class ConferenceListActivity extends InjectableActionBarActivity implements IConferenceListActivity {

    @Inject
    ConferenceController conferenceController;

    @InjectView(R.id.confView)
    RecyclerView mRecyclerView;

    private ConferencesAdapter mAdapter;

    private ConferenceListActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferences);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        ButterKnife.inject(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new ConferenceListActivityPresenter(conferenceController, this);
        presenter.initialize();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_conferences, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void populateConferences(List<ConferenceData> datas) {
        mAdapter = new ConferencesAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void launchConferenceSessionsListActivity(long conferenceId) {
        Timber.d(String.format("Conference Selected: %s", conferenceId));

        Intent conferenceSessionListIntent = new Intent(this, ConferenceSessionListActivity.class);
        conferenceSessionListIntent.putExtra("id", conferenceId);
        startActivity(conferenceSessionListIntent);
    }

    public class ConferencesAdapter extends RecyclerView.Adapter<ConferencesAdapter.ViewHolder> {

        List<ConferenceData> conferenceDatas;

        public ConferencesAdapter(List<ConferenceData> conferenceDatas) {
            this.conferenceDatas = conferenceDatas;
        }

        @Override
        public ConferencesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.conference_list_view_item, parent, false);

            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        public ConferenceData getItem(int position) {
            return conferenceDatas.get(position);
        }

        @Override
        public void onBindViewHolder(ConferencesAdapter.ViewHolder holder, int position) {
            holder.setConferenceData(getItem(position));
        }

        @Override
        public int getItemCount() {
            return conferenceDatas.size();
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @InjectView(R.id.name) public TextView nameView;
            @InjectView(R.id.cityAndState) public TextView cityAndStateView;
            @InjectView(R.id.conferenceImage) public ImageView conferenceImageView;

            private ConferenceData conferenceData;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.inject(this, view);
                view.setOnClickListener(this);
            }

            public void setName(String name) {
                nameView.setText(name);
            }
            public void setCityAndState(String cityAndState) {
                cityAndStateView.setText(cityAndState);
            }

            public void setConferenceData(ConferenceData conferenceData) {
                this.conferenceData = conferenceData;
                setName(conferenceData.getName());
                setCityAndState("Detroit, MI");
                setImage();
            }

            private void setImage() {
                int resId = R.drawable.codelabs_icon;
                int modValue = conferenceData.getId() % 3;

                if (modValue == 0) {
                    resId = R.drawable.codelabs_icon;
                } else if (modValue == 1) {
                    resId = R.drawable.googlestream_icon;
                } else if (modValue == 2) {
                    resId = R.drawable.presentation_icon;
                }

                this.conferenceImageView.setImageResource(resId);
            }

            @Override
            public void onClick(View v) {
                launchConferenceSessionsListActivity(conferenceData.getId());
            }
        }
    }
}
