package com.example.preferencesmicroservice;

import java.util.ArrayList;
import java.util.List;

public class Response2 {
    private Response response;
    private List<Profile> preferences = new ArrayList<>();

    public Response2(Response response, List<Profile> preferences) {
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