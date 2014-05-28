package com.example.conference_android.app.model;

import java.util.Date;

/**
 * Created by carlushenry on 5/28/14.
 */
public class EventSchedule {
    private Long _id;
    private Long eventId;
    private Long eventLeaderId;
    private Long roomId;
    private Date startDttm;
    private Date endDttm;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getEventLeaderId() {
        return eventLeaderId;
    }

    public void setEventLeaderId(Long eventLeaderId) {
        this.eventLeaderId = eventLeaderId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventSchedule that = (EventSchedule) o;

        if (_id != null ? !_id.equals(that._id) : that._id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _id != null ? _id.hashCode() : 0;
    }
}
