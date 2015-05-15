package com.sagetech.conference_android.app.ui.viewModel;

import com.sagetech.conference_android.app.R;

/**
 * Created by jrobertson on 5/15/15.
 */
public enum ConferenceSessionType {
    CODELAB(1, R.drawable.codelabs_icon), STREAMING(2, R.drawable.googlestream_icon), ONSITE(3, R.drawable.presentation_icon);

    private int id;
    private int image;

    private ConferenceSessionType(int id, int image) {
        this.id = id;
        this.image = image;
    }

    public static ConferenceSessionType fromId(int id) {
        for (ConferenceSessionType item : ConferenceSessionType.values()) {
            if (item.id == id) {
                return item;
            }
        }
        return ONSITE;
    }

    public int getImage() {
        return image;
    }
}
