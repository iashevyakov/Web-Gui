package ru.itis.inform.verifiers;


import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.errors.Err;
import ru.itis.inform.models.Country;
import ru.itis.inform.models.Detail;
import ru.itis.inform.models.Mark;
import ru.itis.inform.models.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarksUnitsVerify {

    public static Unit CheckMarksUnits (String mark, String unit)

    {
        if (JDBConnection.getInstance().getConnection()!= null && !mark.equals("")) {

            String req = "SELECT * FROM marksunits WHERE mark_name = ? AND unit_name = ? ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,mark);

                JDBConnection.statement.setString(2,unit);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    return new Unit(resultSet.getString("unit_name"));
                }
            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";

            }
            return null;


        }

        return null;
    }


}
