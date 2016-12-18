package ru.itis.inform.models;


import java.util.ArrayList;

public class Mark {
    //таблица marks
    private String markName;

    private String country;

    private int  year;

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public boolean isonpetrol() {
        return isonpetrol;
    }

    public boolean isautomatic() {
        return isautomatic;
    }

    private boolean isonpetrol;

    public Mark(String markName, String country, int year, boolean isonpetrol, boolean isautomatic) {
        this.markName = markName;
        this.country = country;
        this.year = year;
        this.isonpetrol = isonpetrol;
        this.isautomatic = isautomatic;
    }

    private boolean isautomatic;

    public Mark(String markName){this.markName=markName;}

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }
}
