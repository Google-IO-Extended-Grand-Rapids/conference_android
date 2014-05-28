package com.example.conference_android.app.model;

/**
 * Created by carlushenry on 5/28/14.
 */
public class Sponsor {
    private Long _id;
    private String name;
    private String description;
    private String sponsorshipLevel;
    private Long conference_id;
    private String logo;

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

    public String getSponsorshipLevel() {
        return sponsorshipLevel;
    }

    public void setSponsorshipLevel(String sponsorshipLevel) {
        this.sponsorshipLevel = sponsorshipLevel;
    }

    public Long getConference_id() {
        return conference_id;
    }

    public void setConference_id(Long conference_id) {
        this.conference_id = conference_id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sponsor sponsor = (Sponsor) o;

        if (_id != null ? !_id.equals(sponsor._id) : sponsor._id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return _id != null ? _id.hashCode() : 0;
    }
}
