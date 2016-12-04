package ru.itis.inform.services;

/**
 * Created by Иван on 04.11.2016.
 */
public interface DetailService {
    void updateDetail(String country, String firm, String detail,String number, String mark, String unit, String node);
    public boolean buyDetail(String country, String firm, String detail, String number, String mark, String unit, String node);
}
