package com.sagetech.conference_android.app.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sagetech.conference_android.app.ConferenceApplication;
import com.sagetech.conference_android.app.R;
import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.EventData;
import com.sagetech.conference_android.app.ui.activities.EventDetailActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by danmikita on 5/29/14.
 */
public class FullScheduleFragment extends ListFragment {
    private ConferenceController conferenceController;

    public static FullScheduleFragment newInstance() {
        FullScheduleFragment fullScheduleFragment = new FullScheduleFragment();
        return fullScheduleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.conferenceController = ((ConferenceApplication) getActivity().getApplication()).getConferenceController();

        setListAdapter(new EventsListAdapter(getActivity(), R.layout.list_view_item, conferenceController.getEventData()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent detailActivity = new Intent(getActivity(), EventDetailActivity.class);
        detailActivity.putExtra("id", (Integer) v.getTag());
        startActivity(detailActivity);
    }

    static class EventHolder {
        TextView time;
        TextView title;
        TextView room;
        TextView day;
        ImageView scheduled;
    }

    // ***** LIST ADAPTER [START] ***** //
    private class EventsListAdapter extends ArrayAdapter<EventData> {
        private int layoutResourceId;
        List<EventData> eventDataList;

        public EventsListAdapter(Context context, int resource, List<EventData> eventDataList) {
            super(context, resource, eventDataList);
            this.layoutResourceId = resource;
            this.eventDataList = eventDataList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            EventHolder eventHolder;
            convertView = getActivity().getLayoutInflater().inflate(layoutResourceId, parent, false);

            eventHolder = new EventHolder();
            eventHolder.time = (TextView) convertView.findViewById(R.id.time);
            eventHolder.title = (TextView) convertView.findViewById(R.id.title);
            eventHolder.room = (TextView) convertView.findViewById(R.id.room);
            eventHolder.day = (TextView) convertView.findViewById(R.id.day);
            eventHolder.scheduled = (ImageView) convertView.findViewById(R.id.scheduled);

            if (eventDataList.get(position).isHeader()) {

                eventHolder.time.setVisibility(View.INVISIBLE);
                eventHolder.title.setVisibility(View.INVISIBLE);
                eventHolder.room.setVisibility(View.INVISIBLE);
                eventHolder.scheduled.setVisibility(View.INVISIBLE);
                eventHolder.day.setVisibility(View.VISIBLE);

                eventHolder.day.setText(eventDataList.get(position).getStart_dttm());

            } else {
                convertView.setTag(eventDataList.get(position).getId());

                eventHolder.time.setText(formatDate(eventDataList.get(position).getStart_dttm()));

                eventHolder.title.setText(eventDataList.get(position).getEvent().getTitle());
                if (eventDataList.get(position).getRoom() != null)
                    eventHolder.room.setText(eventDataList.get(position).getRoom().getName());

                if (eventDataList.get(position).getEvent().getEvent_type().equals("Presentation"))
                    eventHolder.scheduled.setImageResource(R.drawable.presentation_icon);
                else if (eventDataList.get(position).getEvent().getEvent_type().equals("Code Lab"))
                    eventHolder.scheduled.setImageResource(R.drawable.codelabs_icon);
            }

            return convertView;
        }

        private String formatDate(String dateString) {
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm aaa", Locale.US);
            return timeFormatter.format(date);
        }
    }
}
