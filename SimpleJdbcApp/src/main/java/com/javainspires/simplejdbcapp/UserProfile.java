package com.javainspires.simplejdbcapp;

public class UserProfile {
    String fname;
    String lname;
    String email;
    String adress;
    String username;
    String position;
    int age;
    String gender;

    public UserProfile(String fname, String lname, String email, String adress, String username, String position, int age, String gender) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.adress = adress;
        this.username = username;
        this.position = position;
        this.age = age;
        this.gender = gender;
    }
}
