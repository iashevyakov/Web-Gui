package ru.itis.inform.services;

/**
 * Created by Иван on 11.12.2016.
 */
public interface MarkService {
    void deleteMark(String mark);
    void addMark(String mark, String country, String year, boolean petrol, boolean automatic);
}
