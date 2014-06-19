package com.example.conference_android.app.model;

/**
 * Created by foxefj on 6/18/14.
 */
public class Token {
    private boolean success;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private String token;
}
