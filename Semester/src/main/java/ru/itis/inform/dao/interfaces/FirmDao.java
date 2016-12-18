package ru.itis.inform.dao.interfaces;

import ru.itis.inform.models.Firm;

import java.util.LinkedList;

/**
 * Created by Иван on 04.11.2016.
 */
public interface FirmDao {
    void addFirm(String firm, String country,String date, String owner);
    void deleteFirm(String firm);
    LinkedList<Firm> findAll();
}
