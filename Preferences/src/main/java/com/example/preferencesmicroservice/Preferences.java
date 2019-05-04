package com.example.preferencesmicroservice;

public class Preferences {
    String morning;
    String afternoon;
    String evening;

    public Preferences(int ID, String morning, String afternoon, String evening) {
        this.morning = morning;
        this.afternoon = afternoon;
        this.evening = evening;
    }

    public Preferences(){

    }

    public String getMorning() {
        return morning;
    }

    public String getAfternoon() {
        return afternoon;
    }

    public String getEvening() {
        return evening;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }

    public void setEvening(String evening) {
        this.evening = evening;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\n\t\"morning\": \"" + morning + "\"" +
                ",\n\t\"afternoon\": \"" + afternoon + "\"" +
                ",\n\t\"evening\": \"" + evening + "\"" +
                "\n}";
    }
}
