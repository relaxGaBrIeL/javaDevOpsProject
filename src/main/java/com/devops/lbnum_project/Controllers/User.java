package com.devops.lbnum_project.Controllers;


public class User {
    private final String fName;
    private final String lName;
    private final String email;


    public User(String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;

    }

    public String getFname() {
        return fName;
    }

    public String getLname() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

}
