package com.example.conference_android.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by danmikita on 5/29/14.
 */
public class PersonalScheduleFragment extends Fragment {
    private String title;
    private int page;

    public static PersonalScheduleFragment newInstance(int page, String title) {
        PersonalScheduleFragment fragmentFirst = new PersonalScheduleFragment();
        Bundle args = new Bundle();
        args.putInt("2", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("2", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_schedule_fragment, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.time);
        tvLabel.setText(page + " -- " + title);
        return view;
    }
}
