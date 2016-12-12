package ru.itis.inform.services;

import java.sql.SQLException;

/**
 * Created by Иван on 04.11.2016.
 */
public interface DetailService {

    void sendDetail( String detail, String number, String mark, String unit, String node);
    void sellDetail(String detail, String number, String mark, String unit, String node);
    void addDetail(String detail, String firm, String node) ;
    void deleteDetail(String detail);
}
