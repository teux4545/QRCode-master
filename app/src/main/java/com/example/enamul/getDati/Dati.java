package com.example.enamul.getDati;

public class Dati {
    private static String[] dati = new String[150];

    private static String[] datitemp = new String[300];

    public String[] getDati() {
        return dati;
    }

    public void setDati(String[] s) {
        dati=s;
    }

    public String[] getDatiTemp() {
        return datitemp;
    }

    public void setDatiTemp(String[] s) {
        datitemp=s;
    }


}
