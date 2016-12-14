package ru.itis.inform.services;

/**
 * Created by Иван on 11.12.2016.
 */
public interface UnitService {
    void deleteUnit(String mark);
    void addUnit(String unit, String inventor_name, String invenotr_country, String date);
}
