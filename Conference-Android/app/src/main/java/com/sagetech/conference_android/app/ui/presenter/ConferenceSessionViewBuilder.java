package com.sagetech.conference_android.app.ui.presenter;

import com.sagetech.conference_android.app.model.ConferenceSessionData;
import com.sagetech.conference_android.app.model.RoomData;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionType;
import com.sagetech.conference_android.app.ui.viewModel.ConferenceSessionViewModel;
import com.sagetech.conference_android.app.ui.viewModel.SessionListItemType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by carlushenry on 3/15/15.
 */
public class ConferenceSessionViewBuilder {
    public static List<ConferenceSessionViewModel> toConferenceSessionViewModel(List<ConferenceSessionData> conferenceSessionDatas, Map<Long, RoomData> roomDatas) {

        Map<Date, List<ConferenceSessionData>> dateMap = mapByDate(conferenceSessionDatas);
        List<ConferenceSessionViewModel> confSessionViewModels = new ArrayList<>();

        if (conferenceSessionDatas == null) {
            return confSessionViewModels;
        }

        for (Map.Entry<Date, List<ConferenceSessionData>> entrySet : dateMap.entrySet()) {

            confSessionViewModels.add(buildDayHeader(entrySet.getKey()));

            for (ConferenceSessionData currConfSession : entrySet.getValue()) {
                Long roomId = currConfSession.getRoomId();
                if (!roomDatas.containsKey(roomId)) {
                    roomId = null;
                }
                RoomData roomData = roomDatas.get(roomId);

                confSessionViewModels.add(toConfrenceSessionViewModel(currConfSession, roomData));
            }
        }

        if (confSessionViewModels.isEmpty()) {
            return confSessionViewModels;
        }

        return orderViewModels(confSessionViewModels);
    }

    private static List<ConferenceSessionViewModel> orderViewModels(List<ConferenceSessionViewModel> conferenceSessionDatas) {
        Comparator<ConferenceSessionViewModel> byDateComparator = new Comparator<ConferenceSessionViewModel>() {
            @Override
            public int compare(ConferenceSessionViewModel lhs, ConferenceSessionViewModel rhs) {
                int i = lhs.getStartDttm().compareTo(rhs.getStartDttm());

                if (i != 0) {
                    return i;
                }

                return lhs.getId().compareTo(rhs.getId());

            }
        };

        TreeSet<ConferenceSessionViewModel> datas = new TreeSet<ConferenceSessionViewModel>(byDateComparator);
        datas.addAll(conferenceSessionDatas);

        return new ArrayList<ConferenceSessionViewModel>(datas);
    }

    private static ConferenceSessionViewModel buildDayHeader(Date date) {
        ConferenceSessionViewModel vModel = new ConferenceSessionViewModel();
        vModel.setStartDttm(date);
        vModel.setListItemType(SessionListItemType.DAYHEADER);
        return vModel;
    }

    private static Map<Date, List<ConferenceSessionData>> mapByDate(List<ConferenceSessionData> conferenceSessionDatas) {
        Map<Date, List<ConferenceSessionData>> map = new HashMap<Date, List<ConferenceSessionData>>();

        for (ConferenceSessionData data : conferenceSessionDatas) {
            Date truncatedDate = truncateDate(data.getStartDttm());

            if (!map.containsKey(truncatedDate)) {
                map.put(truncatedDate, new ArrayList<ConferenceSessionData>());
            }

            map.get(truncatedDate).add(data);
        }

        return map;
    }

    private static ConferenceSessionViewModel toConfrenceSessionViewModel(ConferenceSessionData currConfSession, RoomData roomData) {
        ConferenceSessionViewModel confViewModel = new ConferenceSessionViewModel();

        confViewModel.setId(currConfSession.getId());
        confViewModel.setRoom(roomData.getShortDesc());
        confViewModel.setStartDttm(currConfSession.getStartDttm());
        confViewModel.setTitle(currConfSession.getName());
        confViewModel.setListItemType(SessionListItemType.SESSION);

        if (currConfSession.getConferenceSessionType() != null) {
            confViewModel.setType(ConferenceSessionType.fromId(currConfSession.getConferenceSessionType().getId()));
        }

        return confViewModel;
    }


    private static Date truncateDate(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

}
