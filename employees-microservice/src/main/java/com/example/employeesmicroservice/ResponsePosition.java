package com.example.employeesmicroservice;

import java.util.ArrayList;
import java.util.List;

public class ResponsePosition {
    private Response response;
    private String position;

    public ResponsePosition(Response response, String position) {
        this.response = response;
       this.position=position;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "[\n" +
                "\t" + response +
                ",\n\t{ \n\t\"position\" : "  + "\""+ position+"\"" +
                "\t\n}\n]";
    }

    // equals!!
}
