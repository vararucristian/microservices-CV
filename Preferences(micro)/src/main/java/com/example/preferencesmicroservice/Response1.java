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

    public Response getResponse() {
        return response;
    }

    public List<Preferences> getPreferences() {
        return preferences;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setPreferences(List<Preferences> preferences) {
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return "[\n" +
                "\t" + response +
                ",\n\t" + preferences  +
                "\n]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Response1)) return false;
        Response1 comp = (Response1) obj;
        if(comp.preferences == null && preferences == null)
            return (comp.response.equals(response) && comp.preferences == preferences);
        else return (comp.response.equals(response) && comp.preferences.equals(preferences));
    }
}
