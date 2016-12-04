package ru.itis.inform;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.sql.*;
import java.util.logging.*;




public class JDBCImpl implements UsersDao {

    private Connection connection;

    private String url;

    private String password;

    private String name;

    private Statement statement;

    private ResultSet result1;

    public JDBCImpl() {

         connection = null;
         this.url = "jdbc:postgresql://127.0.0.1:5432/autostudents";
         this.name = "postgres";
         this.password = "ronaldo777";
        try {

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(url, name, password);

            statement = null;

            statement = connection.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public LinkedList<User> findAll() throws FileNotFoundException, SQLException {

        connection = DriverManager.getConnection(url, name, password);

        statement = connection.createStatement();

        LinkedList<User> list = new LinkedList();

        result1 = statement.executeQuery("SELECT * FROM users ");

        User user;

        while (result1.next())
        {

            user = new User(result1.getString("name"),result1.getString("city"),result1.getInt("age"));

            list.addLast(user);

        }

        return list;

    }

    public void save(User user) throws FileNotFoundException, SQLException {
        this.connection = DriverManager.getConnection(url,name, password);
        statement = connection.createStatement();
        String reqpart;
        String request = "INSERT INTO users (name,city,age) VALUES ";
        reqpart = "( '" + user.getName() + "', '" + user.getCity() + "', " + user.getId() + ");";
        request += reqpart;
        reqpart = request;
        statement.executeUpdate(reqpart);
    }

    public User find(int id) throws FileNotFoundException {
        return null;
    }
}
