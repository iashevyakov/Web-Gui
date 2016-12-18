package ru.itis.inform.models;


import java.sql.Date;

public class Firm {
    //таблица firms
    private String firmName;
    private String countryName;

    public String getFirmName() {
        return firmName;
    }

    public String getCountryName() {
        return countryName;
    }

    public Date getFoundation() {
        return foundation;
    }

    public String getOwner() {
        return owner;
    }

    private Date foundation;
    private String owner;

    public Firm(String firmName, String countryName, Date foundation, String owner) {
        this.firmName = firmName;
        this.countryName = countryName;
        this.foundation = foundation;
        this.owner = owner;
    }

    public Firm(String firmName, String countryName){this.firmName=firmName;this.countryName=countryName;}
}
