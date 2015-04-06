package com.sagetech.conference_android.app.ui.viewModel;

import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.PresenterData;
import com.sagetech.conference_android.app.model.RoomData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by carlushenry on 2/19/15.
 */
public class EventDetailViewModel {

    public enum EventType {
        PRESENTATION, CODE_LABS
    }

    private static SimpleDateFormat startDateTimeFormatter = new SimpleDateFormat("EE, MMM d, h");
    private static SimpleDateFormat startMinuteFormatter = new SimpleDateFormat(":mm");
    private static SimpleDateFormat endTimeFormatter = new SimpleDateFormat("h:mm aaa");
    private static String ZERO_MINUTE = ":00";

    private ConferenceSessionData sessionData;
    private RoomData roomData;
    private List<PresenterData> presenterDatas;

    public EventDetailViewModel(ConferenceSessionData sessionData, RoomData roomData, List<PresenterData> presenterDatas) {
        this.sessionData = sessionData;
        this.roomData = roomData;
        this.presenterDatas = presenterDatas;
    }

    public String getEventDateAndDuration() {
        String startMinute = startMinuteFormatter.format(this.getStartDttm());

        if (ZERO_MINUTE.equals(startMinute)) {
            return String.format("%s - %s", startDateTimeFormatter.format(this.getStartDttm()), endTimeFormatter.format(this.getEndDttm()));
        } else {
            return String.format("%s%s - %s", startDateTimeFormatter.format(this.getStartDttm()), startMinute, endTimeFormatter.format(this.getEndDttm()));
        }
    }

    public String getTitle() {
        return sessionData.getName();
    }

    public String getDescription() {
        return sessionData.getFullDesc();
    }

    public String getRoomName() {
        return roomData.getShortDesc();
    }

    public Date getStartDttm() {
        return sessionData.getStartDttm();
    }

    public Date getEndDttm() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(sessionData.getStartDttm());
        cal.add(Calendar.MINUTE, sessionData.getDurationMinutes());
        return cal.getTime();
    }

    public List<EventDetailPresenterView> getPresenters() {
        List<EventDetailPresenterView> views = new ArrayList<EventDetailPresenterView>();

        for (PresenterData presenter : presenterDatas) {
            if (presenter == null) {
                continue;
            }
            views.add(new EventDetailPresenterView(presenter));
        }

        return views;
    }

    public class EventDetailPresenterView {

        private PresenterData presenterData;

        public EventDetailPresenterView(PresenterData presenterData) {
            this.presenterData = presenterData;
        }

        public String getFullName() {
            return String.format("%s %s", this.getFirstName(), this.getLastName());
        }

        public String getFirstName() {
            return presenterData.getId() + "";
        }

        public String getLastName() {
            return presenterData.getUserId();
        }

        public String getBiography() {
            return presenterData.getShortBio();
        }

        public String getPicUrl() {
            return "https://dl.dropboxusercontent.com/s/94j05poa7rg2rf1/UnknownProfile.png";  //TODO: pic url when available
        }
    }

}
