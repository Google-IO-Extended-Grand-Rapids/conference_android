package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlushenry on 3/15/15.
 */
public class ConferenceSessionViewBuilder {
    public static List<ConferenceSessionViewModel> toConferenceSessionViewModel(List<ConferenceSessionData> conferenceSessionDatas) {

        List<ConferenceSessionViewModel> confSessionViewModels = new ArrayList<>();
        if (conferenceSessionDatas == null) {
            return confSessionViewModels;
        }

        for (ConferenceSessionData currConfSession : conferenceSessionDatas) {
            confSessionViewModels.add(toConfrenceSessionViewModel(currConfSession));
        }
        return confSessionViewModels;
    }

    private static ConferenceSessionViewModel toConfrenceSessionViewModel(ConferenceSessionData currConfSession) {
        ConferenceSessionViewModel confViewModel = new ConferenceSessionViewModel();

        confViewModel.setId(currConfSession.getId());
        confViewModel.setRoom("112E");
        confViewModel.setStartDttm(currConfSession.getStartDttm());
        confViewModel.setTitle(currConfSession.getName());

        return confViewModel;
    }


}
