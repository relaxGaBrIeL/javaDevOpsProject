package com.devops.lbnum_project.Controllers.authentication;

import com.devops.lbnum_project.Controllers.User;
import com.devops.lbnum_project.Controllers.socket.SocketConnection;

import java.io.IOException;

public class SignupResponse {
    private final boolean success;
    private final User user;
    private final String message;
    static SocketConnection client;

    public SignupResponse(boolean success, User user, String message) {
        this.success = success;
        this.user = user;
        this.message = message;
    }

    public boolean isSuccess() {
        if (success) {
            try {
                client =    new SocketConnection();
                System.out.println("Connected au serveur !");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return success;
    }

    public User getUser() {
        return user;
    }

    public static SocketConnection getClient() {
        return client;
    }

    public String getMessage() {
        return message;
    }
}
