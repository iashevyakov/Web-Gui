package ru.itis.inform.utils;

import org.apache.commons.codec.digest.DigestUtils;


public class MD5Util {
    //класс и метод для хеширования пароля пользователя.
    public static String md5Apache(String st) {

        String md5Hex = DigestUtils.md5Hex(st);

        return md5Hex;
    }

}
