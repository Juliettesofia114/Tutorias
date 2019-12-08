package com.example.tut.ui.mensajeria;

public class Mensaje {
    private String user;
    private String msn;
    private String hora;

    public Mensaje() {
    }

    public Mensaje(String user, String msn, String hora) {
        this.msn = msn;
        this.user = user;
        this.hora = hora;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
