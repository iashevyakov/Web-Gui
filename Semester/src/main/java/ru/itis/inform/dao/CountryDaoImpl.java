package ru.itis.inform.dao;

import ru.itis.inform.errors.Err;
import ru.itis.inform.verifiers.DetailVerify;
import ru.itis.inform.verifiers.FirmVerify;
import ru.itis.inform.verifiers.NodeVerify;

import java.sql.SQLException;


public class CountryDaoImpl implements CountryDao {

    public void addCountry(String country, String continent, String president, boolean is_federation) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "INSERT INTO countries (country_name,continent,president_name,isfederation) VALUES (?,?,?,?)";


            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, country);

                JDBConnection.statement.setString(2, continent);

                JDBConnection.statement.setString(3, president);

                JDBConnection.statement.setBoolean(4,is_federation);

                JDBConnection.statement.executeUpdate();

                Err.message="THE COUNTRY IS ADDED!";

            } catch (SQLException sql) {

                Err.message="CHECK YOUR ENTERED DATA!";

            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}
    }

    public void deleteCountry(String country) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "SELECT * FROM delete(?,'country') ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, country);

                JDBConnection.statement.executeQuery();

                Err.message="THE COUNTRY IS DELETED!";

            } catch (SQLException sql) {

                Err.message="SORRY! SERVER ERROR!";

            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}
    }
}
