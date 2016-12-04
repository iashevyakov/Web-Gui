package ru.itis.inform.verifiers;

import ru.itis.inform.dao.DetailDao;
import ru.itis.inform.dao.DetailDaoImpl;
import ru.itis.inform.dao.UserDao;
import ru.itis.inform.models.User;
import ru.itis.inform.models.Country;


public class UserVerify {
    public static User checkUserInBD(UserDao userDao, String login) {
        return userDao.findUserLogin(login);
    }
}