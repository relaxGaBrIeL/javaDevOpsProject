package com.devops.lbnum_project.Controllers;

public class LoginResponse {
    private final boolean connected;
    private final User user;

    public LoginResponse(boolean connected, User user) {
        this.connected = connected;
        this.user = user;
    }

    public boolean isConnected() {
        return connected;
    }

    public User getUser() {
        return user;
    }
}
