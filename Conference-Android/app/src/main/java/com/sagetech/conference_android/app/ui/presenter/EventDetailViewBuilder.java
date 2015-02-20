package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.EventData;
import com.sagetech.conference_android.app.model.PresenterData;
import com.sagetech.conference_android.app.model.RoomData;
import com.sagetech.conference_android.app.ui.viewModel.EventDetailView;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by carlushenry on 2/19/15.
 */
public class EventDetailViewBuilder {

    public EventDetailView toEventDetailView(ConferenceSessionData eventData, List<PresenterData> presenterDataList, RoomData roomData) {
        EventDetailView dto = new EventDetailView();
        dto.setDescription(eventData.getFullDesc());
        dto.setStartDttm(eventData.getStartDttm());
        dto.setEndDttm(addMinutes(dto.getStartDttm(), eventData.getDurationMinutes()));
        dto.setTitle(eventData.getName());
        dto.setPresenters(null); // TODO
        dto.setEventType(null); // TODO
        dto.setRoomName(null); // TODO

        return dto;
    }

    private Date addMinutes(Date date, Integer durationMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, durationMinutes);
        return cal.getTime();
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
