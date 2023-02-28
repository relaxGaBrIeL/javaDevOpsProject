package com.devops.lbnum_project.services.authentication;

import com.devops.lbnum_project.services.client.User;
import com.devops.lbnum_project.services.client.SocketConnection;

import java.io.IOException;

public class LoginController {
    private final boolean connected;
    private final User user;
    static SocketConnection client;
    public LoginController(boolean connected, User user) {
        this.connected = connected;
        this.user = user;
    }

    public boolean isConnected() {
        if (connected) {
            try {
                client =    new SocketConnection();
                System.out.println("Connected au serveur !");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return connected;
    }

    public User getUser() {
        return user;
    }
    public static SocketConnection getClient() {
        return client;
    }
}
