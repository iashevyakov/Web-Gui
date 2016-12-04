package ru.itis.inform.errors;


public class Error {
    private static String name;
    private static String message;

    public Error(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public static String getName() {
        return name;
    }

    public static String getMessage() {
        return message;
    }
}