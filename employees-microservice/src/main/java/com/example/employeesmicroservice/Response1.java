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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Response1)) return false;
        Response1 comp = (Response1) obj;
        if(comp.underlings == null && underlings == null)
            return (comp.response.equals(response) && comp.underlings == underlings);
        else return (comp.response.equals(response) && comp.underlings.equals(underlings));
    }
}
