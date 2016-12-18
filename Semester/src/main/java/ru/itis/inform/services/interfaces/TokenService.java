package ru.itis.inform.services.interfaces;


public interface TokenService {


        void addToken(String id, String token);
        void updateToken(String id, String token);
        void deleteToken(String token);
        String findToken(String token);

}
