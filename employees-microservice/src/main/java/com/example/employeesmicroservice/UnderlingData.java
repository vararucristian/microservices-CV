package com.example.employeesmicroservice;

public class UnderlingData {
    private Underling underling;
    private String email;

    public UnderlingData(Underling underling, String mail) {
        this.underling = underling;
        this.email = mail;
    }

    public Underling getUnderling() {
        return underling;
    }

    public void setUnderling(Underling underling) {
        this.underling = underling;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }
    public String toString() {
        return "{\n" +
                "\t\"ID\": " + underling.getID() +
                ",\n\t\"email\": \"" + email + "\""+
                ",\n\t\"job\": \"" + underling.getPosition() + "\"" +
                "\n}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof UnderlingData)) return false;
        UnderlingData comp = (UnderlingData) obj;
        return (comp.underling.equals(underling) && comp.email.equals(email));
    }
}
