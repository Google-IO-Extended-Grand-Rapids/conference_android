package com.sagetech.conference_android.app.ui.viewModel;

import java.util.Date;

/**
 * Created by jrobertson on 3/22/15.
 */
public class ConferenceSessionViewModel {
    private Long id;
    private String name;
    private Date startDttm;
    private String roomShortDesc;

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

    public Date getStartDttm() {
        return startDttm;
    }

    public void setStartDttm(Date startDttm) {
        this.startDttm = startDttm;
    }

    public String getRoomShortDesc() {
        return roomShortDesc;
    }

    public void setRoomShortDesc(String roomShortDesc) {
        this.roomShortDesc = roomShortDesc;
    }



}
