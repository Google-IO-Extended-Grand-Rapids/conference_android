package com.sagetech.conference_android.app.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by carlushenry on 3/5/15.
 */
public class ConferenceSessionsAdapter extends RecyclerView.Adapter<ConferenceSessionsAdapter.ViewHolder> {
    private final List<ConferenceSessionViewModel> conferenceSessions;
    private ConferenceSessionsOnClickListener onClickListener;

    public interface ConferenceSessionsOnClickListener {
        public void clicked(Long id);
    }

    public ConferenceSessionsAdapter(List<ConferenceSessionViewModel> conferenceSessions, ConferenceSessionsOnClickListener onClickListener) {
        this.conferenceSessions = conferenceSessions;
        this.onClickListener = onClickListener;
    }

    @Override
    public ConferenceSessionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ConferenceSessionsAdapter.ViewHolder holder, int position) {
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final SimpleDateFormat DAY_FORMATTER = new SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.US);
        private final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("h:mm a", Locale.US);

        @InjectView(R.id.day) public TextView dayView;
        @InjectView(R.id.time) public TextView timeView;
        @InjectView(R.id.title) public TextView titleView;
        @InjectView(R.id.room) public TextView roomView;
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

        public void setConferenceSessionData(ConferenceSessionViewModel confereneSessionViewModel) {
            this.confereneSessionViewModel = confereneSessionViewModel;
            setDay(confereneSessionViewModel.getDay());
            setTime(confereneSessionViewModel.getTime());
            setTitle(confereneSessionViewModel.getTitle());
            setRoom(confereneSessionViewModel.getRoom()); //TODO -- set to real value once room data is available
        }

        @Override
        public void onClick(View v) {
            onClickListener.clicked(confereneSessionViewModel.getId());
        }
    }
}
