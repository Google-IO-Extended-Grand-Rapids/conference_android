package com.sagetech.conference_android.app.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.ui.presenter.ConferenceSessionListActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceSessionActivity;
import com.sagetech.conference_android.app.model.ConferenceData;
import com.sagetech.conference_android.app.ui.listener.RecyclerItemClickListener;
import com.sagetech.conference_android.app.ui.presenter.ConferenceListActivityPresenter;
import com.sagetech.conference_android.app.ui.presenter.IConferenceListActivity;
import timber.log.Timber;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by adam on 2/21/15.
 */
public class ConferenceSessionListActivity extends InjectableActionBarActivity implements IConferenceSessionActivity {

    @Inject
    ConferenceController conferenceController;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ConferenceSessionListActivityPresenter presenter = null;
    private Long conferenceId;

    @Override
    public void populateConferenceSessions(List<ConferenceSessionData> datas) {
        mAdapter = new ConferenceSessionsAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferences);

        mRecyclerView = (RecyclerView)findViewById(R.id.confView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        presenter = new ConferenceSessionListActivityPresenter(this, conferenceController, conferenceId);
        presenter.initialize();
    }


    public class ConferenceSessionsAdapter extends RecyclerView.Adapter<ConferenceSessionsAdapter.ViewHolder> {

        List<ConferenceSessionData> conferenceSessionDatas;

        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public View mTextView;
            public ViewHolder(View v) {
                super(v);
                mTextView = v;
            }
        }

        public ConferenceSessionsAdapter(List<ConferenceSessionData> conferenceSessionDatas) {
            this.conferenceSessionDatas = conferenceSessionDatas;
        }

        @Override
        public ConferenceSessionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.conference_sesssion_list_view, parent, false);

            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ConferenceSessionsAdapter.ViewHolder holder, int position) {
            TextView txt = (TextView)holder.itemView.findViewById(R.id.name);
            holder.itemView.setTag(conferenceDatas.get(position).getId());
            txt.setText(conferenceDatas.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return conferenceSessionDatas.size();
        }

    }
}
