package com.example.conference_android.app.model;

/**
 * Created by carlushenry on 5/28/14.
 */
public class Attendee {
    private Long _id;
    private String firstName;
    private String lastName;
    private String userId;
    private String password;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendee attendee = (Attendee) o;

        if (_id != null ? !_id.equals(attendee._id) : attendee._id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _id != null ? _id.hashCode() : 0;
    }
}
