package ru.itis.inform.dao;

/**
 * Created by Иван on 05.11.2016.
 */
public interface MarksUnitsDao {

    void addMarksUnits(String mark, String unit);
    void addMark(String mark, String country, int year, boolean petrol, boolean automatic);
    void addUnit(String unit, String inventor_name, String inventor_country, String foundation );
    void deleteUnit(String unit);
    void deleteMark(String mark);
}
