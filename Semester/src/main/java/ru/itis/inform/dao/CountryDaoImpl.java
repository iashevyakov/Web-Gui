package ru.itis.inform.dao;

import java.sql.SQLException;

/**
 * Created by Иван on 04.11.2016.
 */
public class CountryDaoImpl implements CountryDao {

    public void addCountry(String country) {

            if (JDBConnection.getInstance().getConnection() != null && !country.equals("")) {

                String req = "INSERT INTO countries (country_name) VALUES (?)";

                try {
                    JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                    JDBConnection.statement.setString(1, country);

                    JDBConnection.statement.executeUpdate();

                } catch (SQLException sql) {

                    sql.printStackTrace();

                }

            }

    }
}
