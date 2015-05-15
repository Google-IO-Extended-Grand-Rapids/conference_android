package com.sagetech.conference_android.app.model;

import java.util.Date;
import java.util.List;

/**
 * Created by carlushenry on 2/19/15.
 */
public class ConferenceSessionData {

    private Long id;
    private String name;
    private String shortDesc;
    private String fullDesc;
    private Long conferenceId;
    private List<Long> presenterIds;
    private Long roomId;
    private Date startDttm;
    private Integer durationMinutes;
    private Date createDttm;
    private Date lastUpdateDttm;
    private ConferenceSessionTypeData conferenceSessionType;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortDesc() {
        return shortDesc;
    }
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }
    public String getFullDesc() {
        return fullDesc;
    }
    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }
    public Long getConferenceId() {
        return conferenceId;
    }
    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }
    public List<Long> getPresenterIds() {
        return presenterIds;
    }
    public void setPresenterIds(List<Long> presenterIds) {
        this.presenterIds = presenterIds;
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
    public void setStartDttm(Long startDttm) {
        this.startDttm = new Date(startDttm * 1000);
    }
    public Integer getDurationMinutes() {
        return durationMinutes;
    }
    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
    public Date getCreateDttm() {
        return createDttm;
    }
    public void setCreateDttm(Date createDttm) {
        this.createDttm = createDttm;
    }
    public Date getLastUpdateDttm() {
        return lastUpdateDttm;
    }
    public void setLastUpdateDttm(Date lastUpdateDttm) {
        this.lastUpdateDttm = lastUpdateDttm;
    }

    public ConferenceSessionTypeData getConferenceSessionType() {
        return conferenceSessionType;
    }

    public void setConferenceSessionType(ConferenceSessionTypeData conferenceSessionType) {
        this.conferenceSessionType = conferenceSessionType;
    }
}
