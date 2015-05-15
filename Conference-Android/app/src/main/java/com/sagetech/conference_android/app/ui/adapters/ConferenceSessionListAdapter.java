package com.sagetech.conference_android.app.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionType;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;
import com.sagetech.conference_android.app.ui.viewModel.SessionListItemType;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by carlushenry on 3/5/15.
 */
public class ConferenceSessionListAdapter extends RecyclerView.Adapter<ConferenceSessionListAdapter.ViewHolder> {
    private final List<ConferenceSessionViewModel> conferenceSessions;
    private ConferenceSessionListOnClickListener onClickListener;

    public interface ConferenceSessionListOnClickListener {
        public void clicked(Long id);
    }

    public ConferenceSessionListAdapter(List<ConferenceSessionViewModel> conferenceSessions, ConferenceSessionListOnClickListener onClickListener) {
        this.conferenceSessions = conferenceSessions;
        this.onClickListener = onClickListener;
    }

    @Override
    public ConferenceSessionListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 2) {

        }

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getListItemType().getViewType();
    }

    @Override
    public void onBindViewHolder(ConferenceSessionListAdapter.ViewHolder holder, int position) {
        holder.setConferenceSessionData(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public int getItemCount() {
        return conferenceSessions.size();
    }

    public ConferenceSessionViewModel getItem(int position) {
        return conferenceSessions.get(position);
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View v) {
            super(v);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.day) public TextView dayView;
        @InjectView(R.id.time) public TextView timeView;
        @InjectView(R.id.title) public TextView titleView;
        @InjectView(R.id.room) public TextView roomView;
        @InjectView(R.id.iconType) public ImageView iconView;

        private ConferenceSessionViewModel confereneSessionViewModel;

        // each data item is just a string in this case
        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
            v.setOnClickListener(this);
        }

        public void setTitle(final String title) {
            this.titleView.setText(title);
        }

        public void setDay(final String day) {
            this.dayView.setText(day);
        }

        public void setTime(final String time) {
            this.timeView.setText(time);
        }

        public void setRoom(final String room) {
            this.roomView.setText(room);
        }

        public void setIcon(final ConferenceSessionType type) {
            iconView.setImageResource(type.getImage());
        }

        public void setConferenceSessionData(ConferenceSessionViewModel confereneSessionViewModel) {
            this.confereneSessionViewModel = confereneSessionViewModel;

            if (SessionListItemType.DAYHEADER.equals(confereneSessionViewModel.getListItemType()))
            {
                return;
            }

            setDay(confereneSessionViewModel.getDay());
            setTime(confereneSessionViewModel.getTime());
            setTitle(confereneSessionViewModel.getTitle());

            setIcon(confereneSessionViewModel.getType());

            setRoom(confereneSessionViewModel.getRoom());
        }

        @Override
        public void onClick(View v) {
            onClickListener.clicked(confereneSessionViewModel.getId());
        }



    }
}
