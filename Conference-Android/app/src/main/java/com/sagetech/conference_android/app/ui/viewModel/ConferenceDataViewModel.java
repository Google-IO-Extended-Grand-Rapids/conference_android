package com.sagetech.conference_android.app.ui.viewModel;

/**
 * Created by carlushenry on 3/7/15.
 */
public class ConferenceDataViewModel {
    private Integer id;
    private String cityAndState;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
