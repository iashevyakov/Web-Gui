package ru.itis.inform.utils;

import java.security.SecureRandom;

/**
 * Created by Тимур on 10.10.2016.
 */
public class Token {


    public static String getToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }



}