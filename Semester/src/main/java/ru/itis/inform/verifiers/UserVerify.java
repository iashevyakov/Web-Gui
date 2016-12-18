package ru.itis.inform.verifiers;

import ru.itis.inform.dao.interfaces.UserDao;
import ru.itis.inform.errors.Check;
import ru.itis.inform.models.User;

import java.util.regex.Matcher;


public class UserVerify {
    private static Matcher m;
    public static User checkUserInBD(UserDao userDao, String login) {
        return userDao.findUserLogin(login);
    }

    public static boolean check(String username){
        if (username.equals("Username")){
            return false;
        }
        m = Check.parts.matcher(username);
        return m.matches();

    }
}