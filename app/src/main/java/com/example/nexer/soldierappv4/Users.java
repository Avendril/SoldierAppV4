package com.example.nexer.soldierappv4;

/**
 * Created by nexer on 25/04/2018.
 */

public class Users {

    String userID;
    String userName;
    String userGender;

    public Users(){

    }

    public Users(String userID, String userName, String userGender) {
        this.userID = userID;
        this.userName = userName;
        this.userGender = userGender;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }


}
