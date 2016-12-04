package ru.itis.inform;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Иван on 25.09.2016.
 */
public interface AutoDao {

    LinkedList findAll() throws FileNotFoundException, SQLException;

    void save(User user) throws FileNotFoundException;

    User find(int id) throws FileNotFoundException;

}
