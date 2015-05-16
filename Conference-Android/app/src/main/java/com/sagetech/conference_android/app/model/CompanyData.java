package com.sagetech.conference_android.app.model;

/**
 * Created by jrobertson on 5/15/15.
 */
public class CompanyData {
    private long id;
    private String name;
    private String shortDesc;
    private String fullDsc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getFullDsc() {
        return fullDsc;
    }

    public void setFullDsc(String fullDsc) {
        this.fullDsc = fullDsc;
    }
}
