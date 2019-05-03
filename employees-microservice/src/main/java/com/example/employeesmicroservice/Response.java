package com.example.employeesmicroservice;

public class Response {
    private int exitCode;
    private String message;

    public Response(int exitCode, String message) {
        this.exitCode = exitCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"exitCode\": " + exitCode +
                ",\n\t\"message\": \"" + message + "\"" +
                "\n}";
    }
}
