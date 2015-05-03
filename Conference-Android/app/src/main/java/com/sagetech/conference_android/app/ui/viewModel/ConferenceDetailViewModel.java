package com.sagetech.conference_android.app.ui.viewModel;

import android.text.format.DateFormat;

import com.sagetech.conference_android.app.model.ConferenceData;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by carlushenry on 4/2/15.
 */
public class ConferenceDetailViewModel {

    private final ConferenceData confData;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ConferenceDetailViewModel(ConferenceData confData) {
        this.confData = confData;
    }

    public String getName() {
        return confData.getName();
    }

    public String getDateInformation() {
        String startDate = confData.getStartDate();
        String endDate = confData.getEndDate();

        if(startDate == null || startDate.isEmpty() || endDate == null || endDate.isEmpty()) {
            return null;
        }

        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            String startMonth = (String) DateFormat.format("MMMM", start);
            String startDay = (String) DateFormat.format("dd", start);
            String endMonth = (String) DateFormat.format("MMMM", end);
            String endDay = (String) DateFormat.format("dd", end);
            String year = (String) DateFormat.format("yyyy", end);

            if(startMonth != null && startMonth.equals(endMonth)) {
                if(startDay != null && startDay.equals(endDay)) {
                    // May 19 2015
                    return String.format("%s %s %s", startMonth, startDay, year);
                }

                // May 24 - 25 2015
                return String.format("%s %s - %s %s", startMonth, startDay, endDay, year);
            }

            // May 31 - Jun 02 2015
            return String.format("%s %s - %s %s %s", startMonth, startDay, endMonth, endDay, year);
        } catch(Exception ex) {
            // Do nothing, just return the default
        }

        return null;
    }

    public String getFullDescription() {
        return confData.getFullDesc();
    }

    public String getImageUrl() {
        return confData.getImageUrl();
    }

    public String getConferenceContactPerson() {
        return "Mahmoud Ahmadinejad";
    }

}
