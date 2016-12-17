package ru.itis.inform.dao;

import org.postgresql.core.JdbcCallParseInfo;
import ru.itis.inform.errors.Err;
import ru.itis.inform.models.User;

import java.sql.*;
import java.util.LinkedList;


public class UserDaoImpl implements UserDao {

    public void addUser(User user) {

        if (JDBConnection.getInstance().getConnection() != null && user != null) {

            String req = "INSERT INTO users (id,uname,login,upassword,is_admin,is_workman) VALUES (?,?,?,?,?,?) ";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,""+user.getId());

                JDBConnection.statement.setString(2,user.getName());

                JDBConnection.statement.setString(3,user.getLogin());

                JDBConnection.statement.setString(4,user.getPassword());

                JDBConnection.statement.setBoolean(5,user.getIs_admin());

                JDBConnection.statement.setBoolean(6,user.getIs_workman());

                JDBConnection.getInstance().getStatement().executeUpdate();

            } catch (SQLException e) {

                Err.message="CHECK YOUR ENTERED DATA!";

            }
        }
    }

    public User findUserLogin(String login) {

        if (JDBConnection.getInstance().getConnection()!= null && !login.equals("")) {

            String req = "SELECT * FROM users WHERE login= ? ";

            return find(req, login);
        }
        return null;
    }
    public User findUserId(String id) {

       if (JDBConnection.getInstance().getConnection()!= null && !id.equals("")) {

            String req = "SELECT * FROM users WHERE id= ? ";

            return find(req,id);
     }

        return null;

    }

    public LinkedList<User> findAll() throws SQLException {

        LinkedList<User> users = new LinkedList<User>();

        String request = "SELECT * FROM usersview";

        JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

        ResultSet resultSet = JDBConnection.statement.executeQuery();

        while(resultSet.next()) {
            users.add(new User(resultSet.getString("id"),resultSet.getString("uname"), resultSet.getString("login"), resultSet.getString("upassword"), resultSet.getBoolean("is_admin"),resultSet.getBoolean("is_workman")));
        }

        return users;
    }

    public void deleteUser(String username) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "DELETE FROM users WHERE login= ?";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, username);

                JDBConnection.statement.executeUpdate();

                Err.message="The User is Deleted!";

            } catch (SQLException sql) {

                Err.message="SORRY! SERVER ERROR!";

            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}
    }


    public User find(String request, String element) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

            JDBConnection.statement.setString(1,element);

            ResultSet resultSet = JDBConnection.statement.executeQuery();

            while (resultSet.next()) {

                return new User(resultSet.getString("id"),resultSet.getString("uname"), resultSet.getString("login"), resultSet.getString("upassword"), resultSet.getBoolean("is_admin"),resultSet.getBoolean("is_workman"));

            }
        } catch (SQLException sql) {

            Err.message = "SORRY! SERVER ERROR!";

        }
        return null;
    }




}