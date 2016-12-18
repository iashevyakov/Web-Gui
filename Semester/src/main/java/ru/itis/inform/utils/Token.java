package ru.itis.inform.utils;

import java.security.SecureRandom;

public class Token {

    //класс и метод для генерации токена для пользователя.
    public static String getToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }



}