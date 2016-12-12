package ru.itis.inform.errors;

import java.util.regex.Pattern;


public class Check {

    final public static Pattern qCheck = Pattern.compile("^\\d+$");

    final public static Pattern dateCheck = Pattern.compile("^\\d\\d\\d\\d-\\d\\d-\\d\\d$");

    final public static Pattern parts = Pattern.compile("^[a-z]+[0-9]?[0-9]?$");

    final public static Pattern year = Pattern.compile("^1[0-9][0-9][0-9]|200[0-9]|201[0-6]$");

}
