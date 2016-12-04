package ru.itis.inform;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.LinkedList;


public interface UsersDao {

    LinkedList findAll() throws FileNotFoundException, SQLException;

    void save(User user) throws FileNotFoundException, SQLException;

    User find(int id) throws FileNotFoundException;
}
