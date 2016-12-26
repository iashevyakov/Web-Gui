package ru.itis.inform;


import java.util.regex.Pattern;

public class Patterns {

    public static final Pattern REGEXP_COUNT = Pattern.compile("^GET / Ivan/1.0 &(\\-?\\d+)([\\+\\-\\*\\/])(\\-?\\d+)$");

    public static final Pattern REGEXP_MESSAGE  = Pattern.compile("^GET / Ivan/1.0 &(.*)$");

}
