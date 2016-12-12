package ru.itis.inform.dao;

import java.sql.SQLException;

/**
 * Created by Иван on 04.11.2016.
 */
public interface DetailDao {

    void sendDetail(String detail, String number);
    void addDetail(String firm, String detail, String node);
    void sellDetail(String detail, String number) ;
    void deleteDetail(String detail);
}
