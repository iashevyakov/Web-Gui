package ru.itis.inform.dao;

import ru.itis.inform.errors.Err;
import ru.itis.inform.models.Mark;
import ru.itis.inform.models.Unit;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;

import static ru.itis.inform.verifiers.MarkVerify.checkMark;
import static ru.itis.inform.verifiers.UnitVerify.checkUnit;

/**
 * Created by Иван on 05.11.2016.
 */
public class MarksUnitsDaoImpl implements MarksUnitsDao {



    public void addMarksUnits(String mark, String unit) {


        if (JDBConnection.getInstance().getConnection() != null ) {

            String req = "INSERT INTO marksunits (mark_name,unit_name) VALUES (?,?)";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, mark);

                JDBConnection.statement.setString(2, unit);

                JDBConnection.statement.executeUpdate();



            } catch (SQLException sql) {

                Err.message="CHECK YOU ENTERED DATA!";

            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}

    }

    public void addMark(String mark, String country, int year, boolean petrol, boolean automatic) {

        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "INSERT INTO marks (mark_name,country_name,mark_year,isonpetrol,isautomatic) VALUES (?,?,?,?,?)";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, mark);

                JDBConnection.statement.setString(2, country);

                JDBConnection.statement.setInt(3, year);

                JDBConnection.statement.setBoolean(4, petrol);

                JDBConnection.statement.setBoolean(5, automatic);

                JDBConnection.statement.executeUpdate();

                Err.message="THE MARK IS ADDED!";

            } catch (SQLException sql) {

                Err.message="CHECK YOUR ENTERED DATA!";

            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}

    }

    public void addUnit(String unit, String inventor_name, String inventor_country, String foundation ) {

        if (JDBConnection.getInstance().getConnection() != null ) {

            String req = "INSERT INTO units (unit_name,inventor_name,inventor_country,foundation) VALUES (?,?,?,?)";
            try {

                Date d = Date.valueOf(foundation);


                try {
                    JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                    JDBConnection.statement.setString(1, unit);
                    JDBConnection.statement.setString(2, inventor_name);
                    JDBConnection.statement.setString(3, inventor_country);
                    JDBConnection.statement.setDate(4, d);
                    JDBConnection.statement.executeUpdate();
                    Err.message = "THE UNIT IS ADDED!";
                } catch (SQLException sql) {

                    Err.message = "CHECK YOUR ENTERED DATA!";

                }
            }
            catch (Exception e){
                Err.message="IMPOSSIBLE DATE!";
            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}

    }
    public void deleteMark(String mark) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "SELECT * FROM delete(?,'mark') ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, mark);

                JDBConnection.statement.executeQuery();

                Err.message="THE MARK IS DELETED!";

            } catch (SQLException sql) {

                Err.message="SORRY! SERVER ERROR!";

            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}
    }

    public LinkedList<Mark> findAllMarks() {
        return null;
    }

    public LinkedList<Unit> findAllUnits() {
        return null;
    }

    public void deleteUnit(String unit) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "SELECT * FROM delete(?,'unit') ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, unit);

                JDBConnection.statement.executeQuery();

                Err.message="THE UNIT IS DELETED!";

            } catch (SQLException sql) {

                Err.message="SORRY! SERVER ERROR";

            }

        }
        else{
            Err.message="SORRY! SERVER ERROR!";}
    }
}
