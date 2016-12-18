package ru.itis.inform.dao;

import ru.itis.inform.errors.Err;

import java.sql.*;

public class JDBConnection {
    //подключение к БД.
    private static JDBConnection instance = new JDBConnection();
    //объект для подключения к БД
    private Connection connection;
    //объект для выполнения запросов к БД.
    public static PreparedStatement statement;

    private String url = "jdbc:postgresql://localhost:5432/semesterwork";
    //пользователь БД.
    private String name = "main_user1";
    //пароль пользователя БД.
    private String password = "user";

    private JDBConnection() {

        connection = null;

        try {

            Class.forName("org.postgresql.Driver");
            //установка соединения с БД.
            this.connection = DriverManager.getConnection(url, name, password);

        } catch (ClassNotFoundException e) {

            Err.message = "SORRY! SERVER ERROR!";

        } catch (SQLException sql) {

            Err.message = "SORRY! SERVER ERROR!";

        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static JDBConnection getInstance() {
        return instance;
    }

    public PreparedStatement  getStatement() {return statement;}

}