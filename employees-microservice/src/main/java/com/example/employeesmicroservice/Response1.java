package com.example.employeesmicroservice;

import java.util.ArrayList;
import java.util.List;

public class Response1 {
    private Response response;
    private List<UnderlingData> underlings = new ArrayList<>();

    public Response1(Response response, List<UnderlingData> underlings) {
        this.response = response;
        this.underlings = underlings;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "[\n" +
                "\t" + response +
                ",\n\t{ \"underlings\" : "  + underlings  +
                "\n}]";
    }
}
