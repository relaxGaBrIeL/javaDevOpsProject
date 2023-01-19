package com.devops.lbnum_project.Controllers;

public class SignupResponse {
    private boolean success;
    private final User user;
    private final String message;

    public SignupResponse(boolean success, User user, String message) {
        this.success = success;
        this.user = user;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
