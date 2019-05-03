package com.example.employeesmicroservice;

import java.util.ArrayList;
import java.util.List;

public class Response1 {
    private Response response;
    private List<Underlings> underlings = new ArrayList<>();

    public Response1(Response response, List<Underlings> underlings) {
        this.response = response;
        this.underlings = underlings;
    }

    @Override
    public String toString() {
        return "[\n" +
                "\t" + response +
                ",\n\t" + underlings  +
                "\n]";
    }
}
