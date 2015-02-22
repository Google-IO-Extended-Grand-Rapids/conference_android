package com.sagetech.conference_android.app.ui.viewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by carlushenry on 2/19/15.
 */
public class EventDetailView {

    public enum EventType {
        PRESENTATION, CODE_LABS
    }

    private List<EventDetailPresenterView> presenters;
    private String title;
    private String description;
    private String roomName;
    private Date startDttm;
    private Date endDttm;
    private EventType eventType;
    private static SimpleDateFormat timeFormatter1 = new SimpleDateFormat("EEE, h:mm-", Locale.US);
    private static SimpleDateFormat timeFormatter2 = new SimpleDateFormat("h:mm aaa", Locale.US);

    public String getLocationAndStartTime() {

        String locationAndStartTime = null;

        if (roomName == null) {
            locationAndStartTime = String.format("%s%s", timeFormatter1.format(startDttm), timeFormatter2.format(endDttm));
        } else {
            locationAndStartTime = String.format("%s%s in %s", timeFormatter1.format(startDttm), timeFormatter2.format(endDttm), roomName);
        }


        return locationAndStartTime;

    }

    public List<EventDetailPresenterView> getPresenters() {
        return presenters;
    }

    public void setPresenters(List<EventDetailPresenterView> presenters) {
        this.presenters = presenters;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Date getStartDttm() {
        return startDttm;
    }

    public void setStartDttm(Date startDttm) {
        this.startDttm = startDttm;
    }

    public Date getEndDttm() {
        return endDttm;
    }

    public void setEndDttm(Date endDttm) {
        this.endDttm = endDttm;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public class EventDetailPresenterView {
        private String firstName;
        private String lastName;
        private String biography;

        public String getFullName() {
            return String.format("%s %s", firstName, lastName);
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getBiography() {
            return biography;
        }

        public void setBiography(String biography) {
            this.biography = biography;
        }
    }

}
