package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.models.Country;
import ru.itis.inform.models.Mark;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Иван on 05.11.2016.
 */
public class MarkVerify {

    public static Mark checkMark(String mark)
    {
        if (JDBConnection.getInstance().getConnection()!= null && !mark.equals("")) {

            String req = "SELECT * FROM marks WHERE mark_name = ? ";

            return find(req,mark);
        }

        return null;
    }
    public static Mark find(String request, String country) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

            JDBConnection.statement.setString(1,country);

            ResultSet resultSet = JDBConnection.statement.executeQuery();

            while (resultSet.next()) {

                return new Mark(resultSet.getString("mark_name"));
            }
        } catch (SQLException sql) {

            sql.printStackTrace();

        }
        return null;
    }
}
