package com.example.preferencesmicroservice;

import java.util.ArrayList;
import java.util.List;

public class Response1 {
    private Response response;
    private List<Preferences> preferences = new ArrayList<>();

    public Response1(Response response, List<Preferences> preferences) {
        this.response = response;
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return "[\n" +
                "\t" + response +
                ",\n\t" + preferences  +
                "\n]";
    }
}
