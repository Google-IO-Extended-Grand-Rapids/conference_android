package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.model.ConferenceData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceDataViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlushenry on 3/7/15.
 */
public class ConferenceBuilder {

    public List<ConferenceDataViewModel> toConferenceDataViewModel(List<ConferenceData> conferenceDatas) {
        List<ConferenceDataViewModel> confDataViewModels = new ArrayList<>();

        for (ConferenceData currConfData : conferenceDatas) {
            confDataViewModels.add(toConferenceDataViewModel(currConfData));
        }

        return confDataViewModels;
    }

    private ConferenceDataViewModel toConferenceDataViewModel(ConferenceData currConfData) {
        ConferenceDataViewModel confDataViewModel = new ConferenceDataViewModel();

        //TODO Fix this and get the city and state from the location.
        confDataViewModel.setConferenceImageUrl((currConfData.getImageUrl() != null) ? currConfData.getImageUrl() : "https://dl.dropboxusercontent.com/s/453jegu9qnx9l6j/genevieve_pic1.jpg");
        confDataViewModel.setCityAndState("Grand Rapids, MI");
        confDataViewModel.setName(currConfData.getName());
        confDataViewModel.setId(currConfData.getId());

        return confDataViewModel;
    }

}
