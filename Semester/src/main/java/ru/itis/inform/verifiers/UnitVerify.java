package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.errors.Check;
import ru.itis.inform.errors.Err;
import ru.itis.inform.models.Mark;
import ru.itis.inform.models.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;

/**
 * Created by Иван on 05.11.2016.
 */
public class UnitVerify {
    //класс для проверок данных таблицы units.
    private static Matcher m;

    public static Unit checkUnit(String unit) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "SELECT * FROM units WHERE unit_name = ? ";

            return find(req, unit);
        }

        return null;
    }

    public static Unit find(String request, String unit) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

            JDBConnection.statement.setString(1, unit);

            ResultSet resultSet = JDBConnection.statement.executeQuery();

            while (resultSet.next()) {

                return new Unit(resultSet.getString("unit_name"));
            }
        } catch (SQLException sql) {

            Err.message = "SORRY! SERVER ERROR!";
            ;

        }
        return null;
    }

    public static boolean check(String unit, String inventor_name, String inventor_country, String date) {
        if (unit.equals("Unit") || inventor_name.equals("Name of Inventor)") || inventor_country.equals("Country of Inventor") || date.equals("Foundation:YYYY-MM-DD")) {
            return false;
        }

        boolean u = false, in = false, ic = false, d = false;
        m = Check.parts.matcher(unit);
        if (m.matches()) {
            u = true;
        }
        m = Check.parts.matcher(inventor_name);
        if (m.matches()) {
            in = true;
        }
        m = Check.parts.matcher(inventor_country);
        if (m.matches()) {
            ic = true;
        }
        m = Check.dateCheck.matcher(date);
        if (m.matches()) {
            d = true;
        }
        if (u & in & ic & d) {
            return true;
        } else {
            return false;
        }


    }

    public static boolean check(String unit) {
        if (unit.equals("Unit")) {
            return false;
        }
        m = Check.parts.matcher(unit);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
