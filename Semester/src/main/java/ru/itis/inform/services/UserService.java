package ru.itis.inform.services;

import ru.itis.inform.models.User;

import java.util.List;

public interface UserService {
    void add(String name, String login, String password, String passwordAgain, boolean is_admin,boolean is_workman);
    List<User> findAll();
    User find(String login);
    User findId(String id);

}