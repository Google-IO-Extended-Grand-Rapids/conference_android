package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.RoomData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jrobertson on 3/22/15.
 */
public class ConferenceSessionViewBuilder {

    public List<ConferenceSessionViewModel> build(List<ConferenceSessionData> conferenceSessionDatas, Map<Long, RoomData> roomDatas) {

        List<ConferenceSessionViewModel> viewModels = new ArrayList<ConferenceSessionViewModel>();

        for (ConferenceSessionData conferenceSessionData : conferenceSessionDatas) {
            RoomData roomData = roomDatas.get(conferenceSessionData.getRoomId());
            viewModels.add(build(conferenceSessionData, roomData));
        }

        return viewModels;
    }

    private ConferenceSessionViewModel build(ConferenceSessionData confSessionData, RoomData roomData) {
        ConferenceSessionViewModel model = new ConferenceSessionViewModel();

        model.setId(confSessionData.getId());
        model.setName(confSessionData.getName());
        model.setRoomShortDesc(roomData.getShortDesc());
        model.setStartDttm(confSessionData.getStartDttm());

        if (roomData != null) {
            model.setRoomShortDesc(roomData.getShortDesc());
        }

        return model;
    }
}
