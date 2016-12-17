package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.errors.Check;
import ru.itis.inform.models.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;

public class CountryVerify {

    private static Matcher m;

    public static Country checkCountry(String country) {

        if (JDBConnection.getInstance().getConnection()!= null && !country.equals("")) {

            String req = "SELECT * FROM countries WHERE country_name = ? ";

            return find(req,country);
        }

        return null;
    }
    public static Country find(String request, String country) {
        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(request);

            JDBConnection.statement.setString(1,country);

            ResultSet resultSet = JDBConnection.statement.executeQuery();

            while (resultSet.next()) {

                return new Country(resultSet.getString("country_name"),null,null,false);
            }
        } catch (SQLException sql) {

            sql.printStackTrace();

        }
        return null;
    }
    public static boolean  check(String country, String continent, String president){
        if (country.equals("Country")||continent.equals("Continent")||president.equals("President")){
            return false;
        }
        boolean c = false; boolean cont = false; boolean pr = false;
        m = Check.parts.matcher(country);
        if(m.matches()){c=true;}
        m = Check.parts.matcher(continent);
        if (m.matches()){cont=true;}
        m = Check.parts.matcher(president);
        if (m.matches()){pr=true;}
        if(c&cont&pr){return true;}
        else{return false;}
    }
    public static boolean check(String country){
        if (country.equals("Country")){return false;}
        m = Check.parts.matcher(country);
        if(m.matches()){return true;}
        else{return false;}
    }


}
