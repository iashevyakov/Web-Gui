package ru.itis.inform.dao;

import ru.itis.inform.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface UserDao {

    void addUser(User user) throws SQLException;

    User findUserLogin(String login);

    User findUserId(String id);

    LinkedList<User> findAll() throws SQLException;


}