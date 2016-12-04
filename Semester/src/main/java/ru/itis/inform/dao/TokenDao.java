package ru.itis.inform.dao;

public interface TokenDao {

    void addToken(String id, String token);

    void updateToken(String id, String token);

    void deleteToken(String token);

    String findIdByToken(String token);
}