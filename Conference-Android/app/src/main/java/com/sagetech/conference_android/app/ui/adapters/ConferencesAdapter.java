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
import com.squareup.picasso.RequestCreator;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by carlushenry on 3/5/15.
 */
public class ConferencesAdapter extends RecyclerView.Adapter<ConferencesAdapter.ViewHolder> {

    private ConferencesOnClickListener onClickListener;
    private List<ConferenceDataViewModel> conferenceDatas;


    public interface ConferencesOnClickListener {
        public void clicked(Integer conferenceId);
    }

    public ConferencesAdapter(List<ConferenceDataViewModel> conferenceDatas, ConferencesOnClickListener conferencesOnClickListener) {
        this.conferenceDatas = conferenceDatas;
        this.onClickListener = conferencesOnClickListener;
    }

    @Override
    public ConferencesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conference_list_view_item, parent, false);

        return new ViewHolder(v);
    }

    public ConferenceDataViewModel getItem(int position) {
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
            setImage(conferenceData);
        }

        public void setName(String name) {
            nameView.setText(name);
        }

        public void setCityAndState(String cityAndState) {
            cityAndStateView.setText(cityAndState);
        }

        public void setImage(ConferenceDataViewModel conferenceDataViewModel) {
            Picasso picasso = Picasso
                    .with(this.conferenceImageView.getContext());

            RequestCreator reqCreator = null;

            if (conferenceDataViewModel.getConferenceImageUrl() != null) {
                reqCreator = picasso.load(conferenceDataViewModel.getConferenceImageUrl());
            } else {
                reqCreator = picasso.load(conferenceDataViewModel.getConferenceImageDefaultId());
            }

            reqCreator.into(this.conferenceImageView);
        }


        @Override
        public void onClick(View v) {
            onClickListener.clicked(conferenceData.getId());
        }
    }
}
