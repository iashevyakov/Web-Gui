package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.models.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryVerify {

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

                return new Country(resultSet.getString("country_name"));
            }
        } catch (SQLException sql) {

            sql.printStackTrace();

        }
        return null;
    }

}
