package com.example.employeesmicroservice;

import java.util.ArrayList;
import java.util.List;

public class EmployeeResponse {
    Response response;
    private List<Integer> ID = new ArrayList<>();

    public EmployeeResponse(Response response, List<Integer> ID) {
        this.response = response;
        this.ID = ID;
    }

    public List<Integer> getID() {
        return ID;
    }

    public void setID(List<Integer> ID) {
        this.ID = ID;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "[\n" +
                "\t" + response +
                ",\n\t{ \n\t\"ID\" : "  + "\""+ ID+"\"" +
                "\t\n}\n]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof EmployeeResponse)) return false;
        EmployeeResponse comp = (EmployeeResponse) obj;
        return (comp.response.equals(response) && comp.ID == ID);
    }
}
