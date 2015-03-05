package com.sagetech.conference_android.app.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.model.ConferenceData;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by carlushenry on 3/5/15.
 */
public class ConferencesAdapter extends RecyclerView.Adapter<ConferencesAdapter.ViewHolder> {

    List<ConferenceData> conferenceDatas;
    private ConferencesOnClickListener onClickListener;

    public interface ConferencesOnClickListener {
        public void clicked(Integer conferenceId);
    }

    public ConferencesAdapter(List<ConferenceData> conferenceDatas, ConferencesOnClickListener conferencesOnClickListener) {
        this.conferenceDatas = conferenceDatas;
        this.onClickListener = conferencesOnClickListener;
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
        private ConferenceData conferenceData;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(this);
        }

        public void setName(String name) {
            nameView.setText(name);
        }

        public void setConferenceData(ConferenceData conferenceData) {
            this.conferenceData = conferenceData;
            setName(conferenceData.getName());
        }

        @Override
        public void onClick(View v) {
            onClickListener.clicked(conferenceData.getId());
        }
    }
}
