package com.example.Profil;

public class UserProfils {

    String fname;
    String lname;
    String email;
    String adress;
    String username;
    String position;
    int age;
    String gender;

    public UserProfils(String fname, String lname, String email, String adress, String username, String position, int age, String gender) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.adress = adress;
        this.username = username;
        this.position = position;
        this.age = age;
        this.gender = gender;}

    @Override
    public String toString() {
        return "UserProfile{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", username='" + username + '\'' +
                ", position='" + position + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

}


