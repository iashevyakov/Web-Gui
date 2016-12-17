package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.errors.Check;
import ru.itis.inform.errors.Err;
import ru.itis.inform.models.Firm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;

/**
 * Created by Иван on 04.11.2016.
 */
public class FirmVerify {

    private static Matcher m;

    public static Firm checkFirm(String firm, String country){

        if (JDBConnection.getInstance().getConnection()!= null && !firm.equals("")) {

            String req = "SELECT * FROM firms WHERE firm_name = ? AND country_name = ? ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,firm);

                JDBConnection.statement.setString(2,country);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    return new Firm(resultSet.getString("firm_name"),resultSet.getString("country_name"));
                }
            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";

            }
            return null;


        }

        return null;

    }
    public static Firm checkFirm(String firm){

        if (JDBConnection.getInstance().getConnection()!= null) {

            String req = "SELECT * FROM firms WHERE firm_name = ?";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,firm);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    return new Firm(resultSet.getString("firm_name"),resultSet.getString("country_name"));
                }
            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";

            }
            return null;


        }

        return null;

    }
    public static boolean check(String firm, String country, String owner, String date) {
        boolean f = false, c = false, o = false, d = false;
        if (firm.equals("Firm") || country.equals("Country") || owner.equals("Owner") || date.equals("Foundation:YYYY-MM-DD")) {
            return false;
        }
        m = Check.parts.matcher(firm);
        if (m.matches()) {
            f = true;
        }
        m = Check.parts.matcher(country);
        if (m.matches()) {
            c = true;
        }
        m = Check.parts.matcher(owner);
        if (m.matches()) {
            o = true;
        }
        m = Check.dateCheck.matcher(date);
        if (m.matches()) {
            d = true;
        }
        if(f&c&o&d){return true;}
        else{return false;}

    }
    public static boolean check(String Firm){
        if (Firm.equals("Firm"))
        {return false;}
        m = Check.parts.matcher(Firm);
        if (m.matches()){
            return true;
        }
        else {
            return false;
        }

    }

}
