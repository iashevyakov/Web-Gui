package ru.itis.inform.services;

import ru.itis.inform.models.User;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface UserService {
    boolean add(String name, String login, String password,  boolean is_admin,boolean is_workman);
    LinkedList<User> findAll() throws SQLException;
    User find(String login);
    User findId(String id);

}