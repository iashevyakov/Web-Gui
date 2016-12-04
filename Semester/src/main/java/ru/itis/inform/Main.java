package ru.itis.inform;

import ru.itis.inform.utils.MD5Util;

public class Main {
    public static void main(String[] args) {

        String s = "ivan";
        System.out.println(MD5Util.md5Apache(s));

    }
}
