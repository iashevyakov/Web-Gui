package ru.itis.inform.services.interfaces;


public interface CountryService {
    void deleteCountry(String country);
    void addCountry(String country, String continent, String president, boolean is_federation);
    void updateCountry(String country, String newcountry, String continent, String president, boolean is_federation);
}
