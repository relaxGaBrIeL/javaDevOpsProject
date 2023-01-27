package com.devops.lbnum_project.Controllers;

import com.devops.lbnum_project.Controllers.socket.SocketConnection;

import java.io.IOException;

public class SignupResponse {
    private final boolean success;
    private final User user;
    private final String message;

    public SignupResponse(boolean success, User user, String message) {
        this.success = success;
        this.user = user;
        this.message = message;
    }

    public boolean isSuccess() {
        if (success) {
            try {
                SocketConnection socketConnection = new SocketConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return success;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
