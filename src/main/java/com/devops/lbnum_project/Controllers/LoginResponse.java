package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Controllers.socket.SocketConnection;

import java.io.IOException;

public class LoginResponse {
    private final boolean connected;
    private final User user;

    public LoginResponse(boolean connected, User user) {
        this.connected = connected;
        this.user = user;
    }

    public boolean isConnected() {
        if (connected) {
            try {
                SocketConnection socketConnection = new SocketConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return connected;
    }

    public User getUser() {
        return user;
    }






}
