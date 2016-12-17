package ru.itis.inform.models;


public class Country {

    public String getCountryName() {
        return countryName;
    }

    public String getContinent() {
        return continent;
    }

    public String getPresident() {
        return president;
    }

    public boolean isfederation() {
        return isfederation;
    }

    private String countryName;

    private String continent;

    private String president;

    boolean isfederation;

    public Country(String countryName,String continent,String president, boolean isfederation){
        this.countryName=countryName;
        this.continent=continent;
        this.president=president;
        this.isfederation =isfederation;
    }

}
