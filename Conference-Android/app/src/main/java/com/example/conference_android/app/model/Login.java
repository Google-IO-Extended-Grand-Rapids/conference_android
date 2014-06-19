package com.example.conference_android.app.model;

import com.google.gson.annotations.Expose;

/**
 * Created by foxefj on 6/17/14.
 */
public class Login {
    @Expose
    private String username;

    @Expose
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
