package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.models.Mark;
import ru.itis.inform.models.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Иван on 05.11.2016.
 */
public class UnitVerify {

    public static Unit checkUnit(String unit)
    {
        if (JDBConnection.getInstance().getConnection()!= null && !unit.equals("")) {

            String req = "SELECT * FROM units WHERE unit_name = ? ";

            return find(req,unit);
        }

        return null;
    }
    public static Unit find(String request, String unit) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

            JDBConnection.statement.setString(1,unit);

            ResultSet resultSet = JDBConnection.statement.executeQuery();

            while (resultSet.next()) {

                return new Unit(resultSet.getString("unit_name"));
            }
        } catch (SQLException sql) {

            sql.printStackTrace();

        }
        return null;
    }

}
