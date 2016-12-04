package ru.itis.inform.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TokenDaoImpl implements TokenDao {

    public void addToken (String id, String token) {

        if (JDBConnection.getInstance().getConnection() != null && !id.equals("") && !token.equals("")) {

            String req = "INSERT INTO cookies (id,token) VALUES ( ? , ? )";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,id);

                JDBConnection.statement.setString(2,token);

                JDBConnection.getInstance().getStatement().executeUpdate();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }
        }
    }

    public void updateToken(String id, String token) {
        if (JDBConnection.getInstance().getConnection() != null && !id.equals("") && !token.equals("")) {

            String req = "UPDATE cookies SET (token) = ( ? ) "+ "WHERE id = ? ";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,token);

                JDBConnection.statement.setString(2,id);

                JDBConnection.getInstance().getStatement().executeUpdate();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }
        }
    }

    public void deleteToken(String token) {

        if (JDBConnection.getInstance().getConnection() != null && !token.equals("")) {

            String request = "DELETE FROM cookies " + "WHERE token = ? ";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

                JDBConnection.statement.setString(1,token);

                JDBConnection.getInstance().getStatement().executeUpdate();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }
        }
    }

    public String findIdByToken(String token) {

        if (JDBConnection.getInstance().getConnection() != null && !token.equals("")) {

            String request = "SELECT * FROM cookies " + "WHERE token= ? ";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

                JDBConnection.statement.setString(1,token);

                ResultSet resultSet = JDBConnection.getInstance().getStatement().executeQuery();

                while (resultSet.next()) {

                    return resultSet.getString("id");

                }

            } catch (SQLException sql) {

                sql.printStackTrace();
            }

        }

        return null;

    }

}