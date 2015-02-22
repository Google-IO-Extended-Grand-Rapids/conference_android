package com.sagetech.conference_android.app.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceData;
import com.sagetech.conference_android.app.ui.listener.RecyclerItemClickListener;
import com.sagetech.conference_android.app.ui.presenter.ConferenceListActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceListActivity;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class ConferenceListActivity extends InjectableActionBarActivity implements IConferenceListActivity {

    @Inject
    ConferenceController conferenceController;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ConferenceListActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferences);

        mRecyclerView = (RecyclerView)findViewById(R.id.confView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Timber.e(String.format("View Clicked: %s", view.getTag()));
                    }
                })
        );

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


    public class ConferencesAdapter extends RecyclerView.Adapter<ConferencesAdapter.ViewHolder> {

        List<ConferenceData> conferenceDatas;

        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public View mTextView;
            public ViewHolder(View v) {
                super(v);
                mTextView = v;
            }
        }

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

        @Override
        public void onBindViewHolder(ConferencesAdapter.ViewHolder holder, int position) {
            TextView txt = (TextView)holder.itemView.findViewById(R.id.name);
            holder.itemView.setTag(conferenceDatas.get(position).getId());
            txt.setText(conferenceDatas.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return conferenceDatas.size();
        }

    }
}
