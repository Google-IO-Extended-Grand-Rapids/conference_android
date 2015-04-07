package com.sagetech.conference_android.app.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceDataViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by carlushenry on 3/5/15.
 */
public class ConferenceListAdapter extends RecyclerView.Adapter<ConferenceListAdapter.ViewHolder> {

    private ConferenceOnClickListener onClickListener;
    private List<ConferenceDataViewModel> conferenceDatas;


    public interface ConferenceOnClickListener {
        public void clicked(Long conferenceId);
    }

    public ConferenceListAdapter(List<ConferenceDataViewModel> conferenceDatas, ConferenceOnClickListener conferencesOnClickListener) {
        this.conferenceDatas = conferenceDatas;
        this.onClickListener = conferencesOnClickListener;
    }

    @Override
    public ConferenceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conference_list_view_item, parent, false);

        return new ViewHolder(v);
    }

    public ConferenceDataViewModel getItem(int position) {
        return conferenceDatas.get(position);
    }

    @Override
    public void onBindViewHolder(ConferenceListAdapter.ViewHolder holder, int position) {
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
        @InjectView(R.id.name)
        public TextView nameView;
        @InjectView(R.id.cityAndState)
        public TextView cityAndStateView;
        @InjectView(R.id.conferenceImage)
        public ImageView conferenceImageView;
        @InjectView(R.id.conferenceLayout)
        public View conferenceLayout;

        private ConferenceDataViewModel conferenceData;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            conferenceLayout.setOnClickListener(this);
        }

        public void setConferenceData(ConferenceDataViewModel conferenceData) {
            this.conferenceData = conferenceData;
            setName(conferenceData.getName());
            setCityAndState(conferenceData.getCityAndState());
            setImage(conferenceData.getConferenceImageUrl());
        }

        public void setName(String name) {
            nameView.setText(name);
        }

        public void setCityAndState(String cityAndState) {
            cityAndStateView.setText(cityAndState);
        }

        public void setImage(String imgUrl) {
            Picasso
                    .with(this.conferenceImageView.getContext())
                    .load(imgUrl)
                    .placeholder(R.drawable.default_event)
                    .error(R.drawable.default_event)
                    .into(this.conferenceImageView);
        }


        @Override
        public void onClick(View v) {
            onClickListener.clicked(conferenceData.getId());
        }
    }
}
