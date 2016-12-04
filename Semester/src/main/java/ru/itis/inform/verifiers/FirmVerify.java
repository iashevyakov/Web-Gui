package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.models.Firm;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Иван on 04.11.2016.
 */
public class FirmVerify {
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

                sql.printStackTrace();

            }
            return null;


        }

        return null;

    }
}
