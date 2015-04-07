package com.sagetech.conference_android.app.ui.viewModel;

/**
 * Created by carlushenry on 3/7/15.
 */
public class ConferenceDataViewModel {
    private Long id;
    private String cityAndState;
    private String name;
    private String conferenceImageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityAndState() {
        return cityAndState;
    }

    public void setCityAndState(String cityAndState) {
        this.cityAndState = cityAndState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConferenceImageUrl() {
        return conferenceImageUrl;
    }

    public void setConferenceImageUrl(String conferenceImageUrl) {
        this.conferenceImageUrl = conferenceImageUrl;
    }

}
