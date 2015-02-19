package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.api.ConferenceController;
import com.sagetech.conference_android.app.model.EventData;
import com.sagetech.conference_android.app.ui.viewModel.EventDetailView;

/**
 * Created by carlushenry on 2/19/15.
 */
public class EventDetailActivityPresenter {

    private final IEventDetailActivity eventDetailActivity;
    private final ConferenceController conferenceController;
    private final Integer eventId;

    public EventDetailActivityPresenter(IEventDetailActivity eventDetailActivity, ConferenceController conferenceController, Integer eventId) {
        this.eventDetailActivity = eventDetailActivity;
        this.conferenceController = conferenceController;
        this.eventId = eventId;
    }

    public void populateScreen() {
        EventData event = conferenceController.getEvent(eventId);
        eventDetailActivity.populateWithEventDetailView(toEventDetailView(event));
    }

    private EventDetailView toEventDetailView(EventData eventData) {
        EventDetailView dto = new EventDetailView();
        EventData.Event event = eventData.getEvent();
        dto.setDescription(event.getDescription());
        dto.setEndDttm(eventData.getEnd_dttm());
        dto.setEventType(toEventType(event));
        dto.setPresenters(toPresenters(eventData));
        dto.setRoomName((eventData.getRoom() == null ? null : eventData.getRoom().getName()));
        dto.setStartDttm(eventData.getStart_dttm());
        dto.setTitle(event.getTitle());

        return dto;
    }
}
