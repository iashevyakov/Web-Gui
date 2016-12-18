package ru.itis.inform.dao.implementations;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.dao.interfaces.TokenDao;
import ru.itis.inform.errors.Err;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TokenDaoImpl implements TokenDao {
    //класс для работы в БД с токенами.


    //добавляем токен для юзера в БД.
    public void addToken (String id, String token) {

        if (JDBConnection.getInstance().getConnection() != null && !id.equals("") && !token.equals("")) {

            String req = "INSERT INTO cookies (id,token) VALUES ( ? , ? )";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,id);

                JDBConnection.statement.setString(2,token);

                JDBConnection.getInstance().getStatement().executeUpdate();

            } catch (SQLException sql) {
                //если что, создаем ошибку.
                Err.message = "SORRY! SERVER ERROR!";

            }
        }
    }
    //обновляем токен в БД для юзера.
    public void updateToken(String id, String token) {
        if (JDBConnection.getInstance().getConnection() != null ) {

            String req = "UPDATE cookies SET (token) = ( ? ) "+ "WHERE id = ? ";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,token);

                JDBConnection.statement.setString(2,id);

                JDBConnection.getInstance().getStatement().executeUpdate();

            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";

            }
        }
    }
    //удаляем токен для юзера в БД.
    public void deleteToken(String token) {

        if (JDBConnection.getInstance().getConnection() != null ) {

            String request = "DELETE FROM cookies " + "WHERE token = ? ";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

                JDBConnection.statement.setString(1,token);

                JDBConnection.getInstance().getStatement().executeUpdate();

            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";

            }
        }
    }
    //находим юзера по его токену в Cookie.
    public String findIdByToken(String token) {

        if (JDBConnection.getInstance().getConnection() != null) {

            String request = "SELECT * FROM cookies " + "WHERE token= ? ";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

                JDBConnection.statement.setString(1,token);

                ResultSet resultSet = JDBConnection.getInstance().getStatement().executeQuery();

                while (resultSet.next()) {

                    return resultSet.getString("id");

                }

            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";
            }

        }

        return null;

    }

}