package ru.itis.inform.services;

/**
 * Created by Иван on 11.12.2016.
 */
public interface CountryService {
    void deleteCountry(String country);
    void addCountry(String country, String continent, String president, boolean is_federation);
}
