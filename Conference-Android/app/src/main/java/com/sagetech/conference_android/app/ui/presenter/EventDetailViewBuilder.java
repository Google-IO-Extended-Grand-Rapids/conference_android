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
        dto.setPresenters(toPresenters(presenterDataList)); // TODO
        dto.setEventType(null); // TODO
        dto.setRoomName(null); // TODO

        return dto;
    }

    private List<EventDetailView.EventDetailPresenterView> toPresenters(List<PresenterData> presenterDataList) {

        List<EventDetailView.EventDetailPresenterView> presenterList = new ArrayList<EventDetailView.EventDetailPresenterView>();

        for (PresenterData currPresenter : presenterDataList) {
            presenterList.add(toPresenter(currPresenter));
        }


        return presenterList;
    }

    private EventDetailView.EventDetailPresenterView toPresenter(PresenterData currPresenter) {
        EventDetailView.EventDetailPresenterView presenter = new EventDetailView().new EventDetailPresenterView();

        presenter.setFirstName(currPresenter.getId() + "");
        presenter.setLastName(currPresenter.getUserId());
        presenter.setBiography(currPresenter.getShortBio());

        return presenter;
    }

    private Date addMinutes(Date date, Integer durationMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, durationMinutes);
        return cal.getTime();
    }

}
