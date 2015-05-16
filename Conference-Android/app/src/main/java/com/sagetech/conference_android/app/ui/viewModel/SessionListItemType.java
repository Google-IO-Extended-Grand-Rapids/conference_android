package com.sagetech.conference_android.app.ui.viewModel;

/**
 * Created by jrobertson on 5/15/15.
 */
public enum SessionListItemType {
    DAYHEADER(2),SESSION(1);

    private int viewType;

    private SessionListItemType(int viewType) {
        this.viewType = viewType;
    }

    public int getViewType() { return viewType; }
}
