
package ru.itis.inform.services;

import ru.itis.inform.dao.UserDao;
import ru.itis.inform.dao.UserDaoImpl;
import ru.itis.inform.errors.Error;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.messages.Message;
import ru.itis.inform.models.User;
import ru.itis.inform.utils.MD5Util;
import ru.itis.inform.verifiers.UserVerify;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = null;

    public UserServiceImpl() {
        this.userDao = DaoFactory.getInstance().getUserDao();
    }

    public void add(String name, String login, String password, String passwordAgain, boolean is_admin, boolean is_workman) {


            if (password.equals(passwordAgain)) {

                User newUser = null;

                try {

                    password = MD5Util.md5Apache(password);

                    newUser = new User(name, login, password, is_admin, is_workman);

                    if (UserVerify.checkUserInBD(userDao, login) == null) {
                        userDao.addUser(newUser);
                    }
                }
                catch (SQLException e) {

                    e.printStackTrace();

                }
            }

    }

    public List<User> findAll() {
        return null;
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