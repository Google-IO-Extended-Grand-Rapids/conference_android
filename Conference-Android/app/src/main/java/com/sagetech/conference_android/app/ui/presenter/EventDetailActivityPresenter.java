package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.EventData;
import com.sagetech.conference_android.app.ui.viewModel.EventDetailView;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by carlushenry on 2/19/15.
 */
public class EventDetailActivityPresenter {

    private final IEventDetailActivity eventDetailActivity;
    private final ConferenceController conferenceController;
    private final Integer eventId;
    private final EventDetailViewBuilder eventDetailViewBuilder;

    public EventDetailActivityPresenter(IEventDetailActivity eventDetailActivity, ConferenceController conferenceController, Integer eventId) {
        this.eventDetailActivity = eventDetailActivity;
        this.conferenceController = conferenceController;
        this.eventId = eventId;
        this.eventDetailViewBuilder = new EventDetailViewBuilder();
    }

    public void initialize() {
        EventData event = conferenceController.getEvent(eventId);
        EventDetailView eventDetailView = eventDetailViewBuilder.toEventDetailView(event);
        eventDetailActivity.populateWithEventDetailView(eventDetailView);
    }

}
