package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.RoomData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by carlushenry on 3/15/15.
 */
public class ConferenceSessionViewBuilder {
    public static List<ConferenceSessionViewModel> toConferenceSessionViewModel(List<ConferenceSessionData> conferenceSessionDatas, Map<Long, RoomData>roomDatas) {

        List<ConferenceSessionViewModel> confSessionViewModels = new ArrayList<>();
        if (conferenceSessionDatas == null) {
            return confSessionViewModels;
        }

        for (ConferenceSessionData currConfSession : conferenceSessionDatas) {
            RoomData roomData = roomDatas.get(currConfSession.getRoomId());
            confSessionViewModels.add(toConfrenceSessionViewModel(currConfSession, roomData));
        }
        return confSessionViewModels;
    }

    private static ConferenceSessionViewModel toConfrenceSessionViewModel(ConferenceSessionData currConfSession, RoomData roomData) {
        ConferenceSessionViewModel confViewModel = new ConferenceSessionViewModel();

        confViewModel.setId(currConfSession.getId());
        confViewModel.setRoom(roomData.getShortDesc());
        confViewModel.setStartDttm(currConfSession.getStartDttm());
        confViewModel.setTitle(currConfSession.getName());

        return confViewModel;
    }


}
