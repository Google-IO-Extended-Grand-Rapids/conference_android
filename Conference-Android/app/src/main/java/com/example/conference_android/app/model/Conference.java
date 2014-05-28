package com.example.conference_android.app.model;

import java.util.Date;
import java.util.List;

/**
 * Created by carlushenry on 5/28/14.
 */
public class Conference {
    private Long _id;
    private String name;
    private String description;
    private Date startDttm;
    private Date endDttm;
    private List<Sponsor> sponsors;

    public List<ScheduledEvent> getScheduledEvents() {
        return scheduledEvents;
    }

    public void setScheduledEvents(List<ScheduledEvent> scheduledEvents) {
        this.scheduledEvents = scheduledEvents;
    }

    private List<ScheduledEvent> scheduledEvents;

    public Long getId() {

        return _id;
    }

    public void setId(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference that = (Conference) o;

        if (_id != null ? !_id.equals(that._id) : that._id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _id != null ? _id.hashCode() : 0;
    }


}
