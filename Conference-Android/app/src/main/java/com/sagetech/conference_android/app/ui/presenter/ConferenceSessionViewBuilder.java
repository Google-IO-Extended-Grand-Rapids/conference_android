package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.RoomData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;

import java.util.List;

/**
 * Created by jrobertson on 3/22/15.
 */
public class ConferenceSessionViewBuilder {

    public List<ConferenceSessionViewModel> build(List<ConferenceSessionData> conferenceSessionDatas, List<RoomData> roomDatas) {




        return null;

    }

    public ConferenceSessionViewModel build(ConferenceSessionData confSessionData, RoomData roomData) {
        ConferenceSessionViewModel model = new ConferenceSessionViewModel();

        model.setId(confSessionData.getId());
        model.setName(confSessionData.getName());
        model.setRoomShortDesc(roomData.getShortDesc());
        model.setStartDttm(confSessionData.getStartDttm());

        return model;
    }
}
