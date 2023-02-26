package com.devops.lbnum_project.Controllers;


public class User {
    private static String fName;
    private static String lName;
    private  String email;

    private static int UserId;

    private String userName;

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int id,String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        UserId =id;

    }
    public User(String fName, String lName, String email) {
        User.fName = fName;
        this.userName = lName;
        this.email = email;

    }

    public static  String getFname() {
        return fName;
    }

    public static String getLname() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public static int getUserId() {
        return UserId;
    }
}
