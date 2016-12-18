package ru.itis.inform.errors;

import java.util.regex.Pattern;


public class Check {
    //класс для проверки правильнссти введения данных в полях.


    final public static Pattern qCheck = Pattern.compile("^\\d+$");//проверка на введение положительного количества.

    final public static Pattern dateCheck = Pattern.compile("^\\d\\d\\d\\d-\\d\\d-\\d\\d$");//проверка на правильность date п о формату YYYY-MM-DD, остальное(например, месяц<13) на БД.

    final public static Pattern parts = Pattern.compile("^[a-z]+[0-9]?[0-9]?$");//проверка для имен объектов таблиц detail_name, firm_name и т.д.(остальное, например, длина <15 - на БД.)

    final public static Pattern year = Pattern.compile("^1[0-9][0-9][0-9]|200[0-9]|201[0-6]$");//проверка на правильность года

}
