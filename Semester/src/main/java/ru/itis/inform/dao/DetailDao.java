package ru.itis.inform.dao;

import ru.itis.inform.models.Country;
import ru.itis.inform.models.Detail;
import ru.itis.inform.models.DetailsView;
import java.sql.SQLException;
import java.util.LinkedList;

public interface DetailDao {

    void sendDetail(String detail, String number);
    void addDetail(String firm, String detail, String node);
    void sellDetail(String detail, String number) ;
    void deleteDetail(String detail);
    LinkedList<DetailsView> findAll();
}
