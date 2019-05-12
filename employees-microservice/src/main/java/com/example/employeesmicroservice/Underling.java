package com.example.employeesmicroservice;

public class Underling {
    int ID;
    String position;

    public Underling(int ID, String position) {
        this.ID = ID;
        this.position = position;
    }

    public Underling(){

    }

    public int getID() {
        return ID;
    }

    public String getPosition() {
        return position;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"ID\": " + ID +
                ",\n\t\"position\": \"" + position + "\"" +
                "\n}";
    }
}
