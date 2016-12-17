package ru.itis.inform.dao;

import ru.itis.inform.errors.Err;
import ru.itis.inform.models.Firm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Иван on 04.11.2016.
 */
public class FirmDaoImpl implements FirmDao {

    private LinkedList<Firm> firms;


    public void addFirm(String firm, String country,String date, String owner) {

        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "INSERT INTO firms (firm_name,country_name,foundation,owner_name) VALUES (?,?,?,?)";

            try {
                Date d = Date.valueOf(date);


            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, firm);

                JDBConnection.statement.setString(2, country);

                JDBConnection.statement.setDate(3, d);

                JDBConnection.statement.setString(4, owner);

                JDBConnection.statement.executeUpdate();

                Err.message="THE FIRM IS ADDED!";

            } catch (SQLException sql) {

                Err.message = "CHECK YOU ENTERED DATA!";


            }

            }
            catch (Exception e){
                Err.message="IMPOSSIBLE DATE!";
            }

        }
        else{
            Err.message="SORRY! SERVER ERROR!";}
    }
    public void deleteFirm(String firm) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "SELECT * FROM delete(?,'firm') ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, firm);

                JDBConnection.statement.executeQuery();

                Err.message="THE FIRM IS DELETED!";

            } catch (SQLException sql) {

                Err.message="SORRY! SERVER ERROR!";

            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}
    }

    public LinkedList<Firm> findAll() {
        if (JDBConnection.getInstance().getConnection() != null) {

            firms = new LinkedList<Firm>();

            String req = "SELECT * FROM firms_view";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while(resultSet.next()){

                    firms.add(new Firm(resultSet.getString("firm_name"),resultSet.getString("country_name"),resultSet.getDate("foundation"),resultSet.getString("owner_name")));

                }

                return firms;
            } catch (SQLException sql) {

                Err.message="SORRY! SERVER ERROR!";
                return null;
            }
        }
        else{Err.message="SORRY! SERVER ERROR!";return null;}
    }
}
