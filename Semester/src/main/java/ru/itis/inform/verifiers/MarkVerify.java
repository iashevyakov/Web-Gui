package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.errors.Check;
import ru.itis.inform.models.Country;
import ru.itis.inform.models.Mark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;

/**
 * Created by Иван on 05.11.2016.
 */
public class MarkVerify {

    public static Matcher m;

    public static Mark checkMark(String mark) {
        if (JDBConnection.getInstance().getConnection() != null && !mark.equals("")) {

            String req = "SELECT * FROM marks WHERE mark_name = ? ";

            return find(req, mark);
        }

        return null;
    }

    public static Mark find(String request, String country) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

            JDBConnection.statement.setString(1, country);

            ResultSet resultSet = JDBConnection.statement.executeQuery();

            while (resultSet.next()) {

                return new Mark(resultSet.getString("mark_name"));
            }
        } catch (SQLException sql) {

            sql.printStackTrace();

        }
        return null;
    }

    public static boolean check(String mark, String country, String year) {
        boolean ma = false, c = false, y = false;
        if (mark.equals("Mark") || country.equals("Country") || year.equals("Year : YYYY")) {
            return false;
        }
        m = Check.parts.matcher(mark);
        if (m.matches()) {
            ma = true;
        }
        m = Check.parts.matcher(country);
        if (m.matches()) {
            c = true;
        }
        m= Check.year.matcher(year);
        if (m.matches()){
            y = true;
        }
        if (ma&c&y){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean check(String mark){
        if (mark.equals("Mark")){
            return false;
        }
        m = Check.parts.matcher(mark);
        if(m.matches()){
            return true;
        }
        else{
            return false;
        }
    }
}

