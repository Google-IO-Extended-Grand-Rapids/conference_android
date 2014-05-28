package com.example.conference_android.app.model;

/**
 * Created by carlushenry on 5/28/14.
 */
public class Event {
    private Long _id;
    private String title;
    private String description;
    private String eventType;
    private Long conferenceId;

    public Long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (_id != null ? !_id.equals(event._id) : event._id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _id != null ? _id.hashCode() : 0;
    }
}
