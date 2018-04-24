package com.example.nexer.soldierappv4;

/**
 * Created by nexer on 23/04/2018.
 */

public class userInfo {
    public String Name,Surname,Age,Email;


    public userInfo(String Name, String Surname, String age, String Email){
        this.Name = Name;
        this.Surname = Surname;
        this.Age = age;
        this.Email = Email;
    }

    public String getName(){
        return this.Name;
    }
    public String getSurname(){
        return this.Surname;
    }
    public String getAge(){
        return this.Age;
    }
    public String getEmail(){
        return this.Email;
    }
}
