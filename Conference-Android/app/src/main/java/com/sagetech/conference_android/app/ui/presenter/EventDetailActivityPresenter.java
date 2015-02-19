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

    public EventDetailActivityPresenter(IEventDetailActivity eventDetailActivity, ConferenceController conferenceController, Integer eventId) {
        this.eventDetailActivity = eventDetailActivity;
        this.conferenceController = conferenceController;
        this.eventId = eventId;
    }

    public void initialize() {
        EventData event = conferenceController.getEvent(eventId);
        eventDetailActivity.populateWithEventDetailView(toEventDetailView(event));
    }

    private EventDetailView toEventDetailView(EventData eventData) {
        EventDetailView dto = new EventDetailView();
        EventData.Event event = eventData.getEvent();
        dto.setDescription(event.getDescription());
        dto.setEndDttm(toDate(eventData.getEnd_dttm()));
        dto.setEventType(toEventType(event));
        dto.setPresenters(toPresenters(eventData));
        dto.setRoomName((eventData.getRoom() == null ? null : eventData.getRoom().getName()));
        dto.setStartDttm(toDate(eventData.getStart_dttm()));
        dto.setTitle(event.getTitle());

        return dto;
    }

    private List<EventDetailView.EventDetailPresenterView> toPresenters(EventData eventData) {

        List<EventDetailView.EventDetailPresenterView> presenterList = new ArrayList<EventDetailView.EventDetailPresenterView>();

        List<EventData.Event_leader> eventLeaders = eventData.getEvent_leaders();

        for (EventData.Event_leader currEventLeader : eventLeaders) {
            presenterList.add(toPresenter(currEventLeader));
        }


        return presenterList;
    }

    private EventDetailView.EventDetailPresenterView toPresenter(EventData.Event_leader currEventLeader) {
        EventDetailView.EventDetailPresenterView presenter = new EventDetailView().new EventDetailPresenterView();

        presenter.setFirstName(currEventLeader.getFirst_name());
        presenter.setLastName(currEventLeader.getLast_name());
        presenter.setBiography(currEventLeader.getBiography());

        return presenter;
    }

    private Date toDate(String dateString) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(dateString, new ParsePosition(0));
    }

    private EventDetailView.EventType toEventType(EventData.Event event) {
        EventDetailView.EventType eventType = null;
        if ("Presentation".equals(event.getEvent_type())) {
            eventType = EventDetailView.EventType.PRESENTATION;
        } else if ("Code Lab".equals(event.getEvent_type())) {
            eventType = EventDetailView.EventType.CODE_LABS;
        }
        return eventType;
    }
}
