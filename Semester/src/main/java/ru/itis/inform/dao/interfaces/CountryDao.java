package ru.itis.inform.dao.interfaces;

import ru.itis.inform.models.Country;

import java.util.LinkedList;

/**
 * Created by Иван on 04.11.2016.
 */
public interface CountryDao {

    void addCountry(String country, String continent, String president, boolean is_federation);
    void deleteCountry(String country);
    LinkedList<Country> findAll();
    void updateCountry(String country, String newcountry, String continent, String president, boolean isfederation);

}
