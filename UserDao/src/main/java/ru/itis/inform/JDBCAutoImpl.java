package ru.itis.inform;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.sql.*;


public class JDBCAutoImpl implements AutoDao {

    Connection connection;
    String url;
    String password;
    String name;
    Statement statement;
    ResultSet result1;

    public JDBCAutoImpl() {

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

    public LinkedList<Auto> findAll() throws FileNotFoundException, SQLException {

        connection = DriverManager.getConnection(url, name, password);

        statement = connection.createStatement();

        LinkedList<Auto> list = new LinkedList();

        result1 = statement.executeQuery("SELECT * FROM auto  ");

        Auto auto;

        while (result1.next()) {

            auto = new Auto(result1.getString("model"));

            list.addLast(auto);
        }

        return list;

    }

    public void save(User user) throws FileNotFoundException {


    }

    public User find(int id) throws FileNotFoundException {
        return null;
    }
}