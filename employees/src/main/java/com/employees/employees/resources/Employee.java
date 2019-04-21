package com.employees.employees.resources;

public class Employee {
    private String firstName;
    private String lastName;
    private int id;
    private String position;

    @Override
    public String toString() {
        return "{" +
                "\"firstName\":\"" + firstName + '\"' +
                ", \"lastName\":\"" + lastName + '\"' +
                ", \"id\":" + id +
                ", \"position\":\"" + position + '\"' +
                '}';
    }

    public Employee(String fName, String lName, int id, String position) {
        this.firstName = fName;
        this.lastName = lName;
        this.id = id;
        this.position = position;
    }

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
