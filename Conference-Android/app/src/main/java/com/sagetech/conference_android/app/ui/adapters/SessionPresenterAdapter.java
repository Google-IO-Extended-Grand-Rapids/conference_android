package com.sagetech.conference_android.app.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.ui.viewModel.EventDetailViewModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jrobertson on 4/4/15.
 */
public class SessionPresenterAdapter extends RecyclerView.Adapter<SessionPresenterAdapter.ViewHolder> {

    private List<EventDetailViewModel.EventDetailPresenterView> presenterViews;

    public SessionPresenterAdapter(List<EventDetailViewModel.EventDetailPresenterView> presenterViews) {
        this.presenterViews = presenterViews;
    }

    @Override
    public SessionPresenterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.presenter_list_item, parent, false);

        return new ViewHolder(v);
    }

    public EventDetailViewModel.EventDetailPresenterView getItem(int position) {
        return presenterViews.get(position);
    }

    @Override
    public void onBindViewHolder(SessionPresenterAdapter.ViewHolder holder, int position) {
        holder.setPresenterView(getItem(position));
    }

    @Override
    public int getItemCount() {
        return presenterViews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.imgSpeaker)
        ImageView pic;

        @InjectView(R.id.txtPresenterName)
        TextView name;

        @InjectView(R.id.txtPresenterCompany)
        TextView company;

        @InjectView(R.id.txtBio)
        TextView bio;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setPresenterView(EventDetailViewModel.EventDetailPresenterView presenter) {
            setName(presenter.getFullName());
            setCompany("Company Name"); //TODO: set company name when available
            setBio(presenter.getBiography());
        }

        private void setPic(String imageUrl) {
        }

        private void setName(String name) {
            this.name.setText(name);
        }

        private void setCompany(String company) {
            this.company.setText(company);
        }

        private void setBio(String bio) {
            this.bio.setText(bio);
        }
    }
}
