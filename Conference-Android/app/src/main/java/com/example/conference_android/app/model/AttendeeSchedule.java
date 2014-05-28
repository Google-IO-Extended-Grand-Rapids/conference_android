package com.example.conference_android.app.model;

/**
 * Created by carlushenry on 5/28/14.
 */
public class AttendeeSchedule {
    private Long _id;
    private Long attendeeId;
    private Long eventScheduleId;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(Long attendeeId) {
        this.attendeeId = attendeeId;
    }

    public Long getEventScheduleId() {
        return eventScheduleId;
    }

    public void setEventScheduleId(Long eventScheduleId) {
        this.eventScheduleId = eventScheduleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttendeeSchedule that = (AttendeeSchedule) o;

        if (_id != null ? !_id.equals(that._id) : that._id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _id != null ? _id.hashCode() : 0;
    }
}
