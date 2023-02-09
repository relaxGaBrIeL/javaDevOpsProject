package com.devops.lbnum_project.Controllers;


public class User {
    private  String fName;
    private  String lName;
    private  String email;


    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
