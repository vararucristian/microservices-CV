package com.example.employeesmicroservice;

import java.util.ArrayList;
import java.util.List;

public class ResponsePosition {
    private Response response;
    private String position;

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ResponsePosition)) return false;
        ResponsePosition comp = (ResponsePosition) obj;
        if( comp.getPosition() == null && position == null)
            return (comp.response.equals(response) );
        else return (comp.response.equals(response) && comp.position.equals(position));
    }
}
