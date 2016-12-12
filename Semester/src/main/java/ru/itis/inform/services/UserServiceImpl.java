
package ru.itis.inform.services;

import ru.itis.inform.dao.UserDao;
import ru.itis.inform.factories.DaoFactory;

import ru.itis.inform.models.User;
import ru.itis.inform.utils.MD5Util;
import ru.itis.inform.verifiers.UserVerify;

import java.sql.SQLException;
import java.util.LinkedList;

public class UserServiceImpl implements UserService {

    private UserDao userDao = null;

    public UserServiceImpl() {
        this.userDao = DaoFactory.getInstance().getUserDao();
    }

    public boolean add(String name, String login, String password, boolean is_admin, boolean is_workman) {


        User newUser = null;

        try {

            password = MD5Util.md5Apache(password);

            newUser = new User(name, login, password, is_admin, is_workman);

            if (UserVerify.checkUserInBD(userDao, login) == null) {
                userDao.addUser(newUser);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }




    public LinkedList<User> findAll() throws SQLException {
        LinkedList<User> list = new LinkedList<User>();
        list = userDao.findAll();
        return list;
    }



    public User find(String login) {

        if (userDao.findUserLogin(login) == null) {

            return null;
        } else {
            return userDao.findUserLogin(login);
        }
    }

    public User findId(String id) {

        if (userDao.findUserId(id) == null) {
            return null;
        } else {
            return userDao.findUserId(id);
        }
    }



}