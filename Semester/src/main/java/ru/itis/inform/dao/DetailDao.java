package ru.itis.inform.dao;

/**
 * Created by Иван on 04.11.2016.
 */
public interface DetailDao {

    void updateDetail(String detail,String number);
    void addDetail(String firm, String detail,String number, String node);
    void buyDetail(String detail, String number);
}
