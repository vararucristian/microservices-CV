package com.example.employeesmicroservice;

import org.springframework.http.HttpStatus;

public class Response {
    private int exitCode;
    private String message;
    private HttpStatus status;

    public Response(int exitCode, String message, HttpStatus status) {
        this.exitCode = exitCode;
        this.message = message;
        this.status = status;
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getMessage() {
        return message;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"exitCode\": " + exitCode +
                ",\n\t\"message\": \"" + message + "\"" +
                "\n}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Response)) return false;
        Response comp = (Response) obj;
        return (comp.exitCode == exitCode && comp.message.equals(message) && comp.status == status);
    }
}
