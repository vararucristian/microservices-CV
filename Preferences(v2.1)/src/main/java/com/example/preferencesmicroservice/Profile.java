package com.example.preferencesmicroservice;

public class Profile {
    String nume;
    String prenume;
    String email;
    String parola;
    String data_inregistrare;

    public Profile(int id, String nume, String prenume, String email,String parola,String data_inregistrare) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.parola=parola;
        this.data_inregistrare=data_inregistrare;
    }

    public Profile(){

    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {return prenume; }

    public String getEmail() {
        return email;
    }

    public String getParola() {return parola; }

    public String getData_inregistrare() {
        return data_inregistrare;
    }

    public void setNume(String nume) {this.nume = nume;}

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public void setData_inregistrare(String data_inregistrare) {
        this.data_inregistrare = data_inregistrare;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\n\t\"nume\": \"" + nume + "\"" +
                ",\n\t\"prenume\": \"" + prenume + "\"" +
                ",\n\t\"email\": \"" + email + "\"" +
                ",\n\t\"parola\": \"" + parola + "\"" +
                ",\n\t\"data_inregistrare\": \"" + data_inregistrare + "\"" +
                "\n}";
    }
}
