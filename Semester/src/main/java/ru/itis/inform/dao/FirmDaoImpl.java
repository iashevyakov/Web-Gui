package ru.itis.inform.dao;

import java.sql.SQLException;

/**
 * Created by Иван on 04.11.2016.
 */
public class FirmDaoImpl implements FirmDao {

    public void addFirm(String firm, String country) {

        if (JDBConnection.getInstance().getConnection() != null && !country.equals("")) {

            String req = "INSERT INTO firms (firm_name,country_name) VALUES (?,?)";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, firm);

                JDBConnection.statement.setString(2, country);

                JDBConnection.statement.executeUpdate();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }

        }
    }
}
