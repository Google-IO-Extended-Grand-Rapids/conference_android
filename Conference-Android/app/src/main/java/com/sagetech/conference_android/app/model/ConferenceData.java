package com.sagetech.conference_android.app.model;

/**
 * Created by jrobertson on 2/18/15.
 */
public class ConferenceData {
    private Integer id;
    private String name;
    private String shortDesc;
    private String fullDesc;
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setShortDesc(String short_desc) {
        this.shortDesc = short_desc;
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public void setFullDesc(String full_desc) {
        this.fullDesc = full_desc;
    }

}
