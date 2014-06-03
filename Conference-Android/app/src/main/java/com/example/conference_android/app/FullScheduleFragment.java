package com.example.conference_android.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.conference_android.app.api.ConferenceController;
import com.example.conference_android.app.model.EventData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by danmikita on 5/29/14.
 */
public class FullScheduleFragment extends ListFragment {
    private static final String TAG = "FullScheduleFragment";
    private ConferenceController conferenceController;
    private Subscription subscription;

    public static FullScheduleFragment newInstance(int page, String title) {
        FullScheduleFragment fragmentFirst = new FullScheduleFragment();
        Bundle args = new Bundle();
        args.putInt("1", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.conferenceController = ((ConferenceApplication) getActivity().getApplication()).getConferenceController();

        subscription = Observable.create(new Observable.OnSubscribe<List<EventData>>() {
            @Override
            public void call(Subscriber<? super List<EventData>> subscriber) {
                try {
                    Log.i(TAG, "Looking up first event leader");
                    List<EventData> events = conferenceController.getEvents();
                    subscriber.onNext(events);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<EventData>>() {
                    @Override
                    public void call(List<EventData> o) {
                        Log.i(TAG, "Updating Screen");
                        setListAdapter(new EventsListAdapter(getActivity(), R.layout.full_schedule_fragment, o));
                    }
                });
        Log.i(TAG, "Completed the onCreate() method");
    }

    @Override
    public void onDestroy() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    static class EventHolder {
        TextView time;
        TextView title;
        TextView room;
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

            eventHolder.time.setText(formatDate(eventDataList.get(position).getStart_dttm()));
            eventHolder.title.setText(eventDataList.get(position).getEvent().getTitle());
            eventHolder.room.setText(eventDataList.get(position).getRoom().getName());

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
