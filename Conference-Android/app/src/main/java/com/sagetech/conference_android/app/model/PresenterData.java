package com.sagetech.conference_android.app.model;

/**
 * Created by carlushenry on 2/19/15.
 */
public class PresenterData {

    private Long id;
    private String userId;
    private String shortBio;
    private String jobTitle;
    private String profilePicUrl;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private CompanyData companyView;

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public CompanyData getCompanyView() {
        return companyView;
    }

    public void setCompanyView(CompanyData companyView) {
        this.companyView = companyView;
    }
}
