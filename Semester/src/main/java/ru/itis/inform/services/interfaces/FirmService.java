package ru.itis.inform.services.interfaces;

/**
 * Created by Иван on 11.12.2016.
 */
public interface FirmService {
    void addFirm(String firm, String country,String date, String owner);
    void deleteFirm(String firm);
}
