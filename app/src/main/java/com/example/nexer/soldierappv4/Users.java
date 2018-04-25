package com.example.nexer.soldierappv4;

/**
 * Created by nexer on 25/04/2018.
 */

public class Users {

    String userID;
    String userName;
    String userSurname;
    String userAddress;
    String userNationality;
    String userGender;

    public Users(){

    }

    public Users(String userID, String userName, String userSurname, String userAddress, String userNationality, String userGender) {
        this.userID = userID;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAddress = userAddress;
        this.userNationality = userNationality;
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

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserNationality() {
        return userNationality;
    }

    public void setUserNationality(String userNationality) {
        this.userNationality = userNationality;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
}
