package ru.itis.inform.dao;

import java.sql.SQLException;

import static ru.itis.inform.verifiers.MarkVerify.checkMark;
import static ru.itis.inform.verifiers.UnitVerify.checkUnit;

/**
 * Created by Иван on 05.11.2016.
 */
public class MarksUnitsDaoImpl implements MarksUnitsDao {

    public void addMarksUnits(String mark, String unit)
    {

        if (checkMark(mark)!=null && checkUnit(unit)!=null){addBoth(mark,unit);}
        if (checkMark(mark)!=null && checkUnit(unit)==null){addUnit(unit);addBoth(mark,unit);}
        if (checkMark(mark)==null && checkUnit(unit)==null){addMark(mark);addUnit(unit);addBoth(mark,unit);}
        if (checkMark(mark)==null && checkUnit(unit)!=null){addMark(mark);addBoth(mark,unit);}

    }

    public void addBoth(String mark, String unit) {


        if (JDBConnection.getInstance().getConnection() != null && !mark.equals("")) {

            String req = "INSERT INTO marksunits (mark_name,unit_name) VALUES (?,?)";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, mark);

                JDBConnection.statement.setString(2, unit);

                JDBConnection.statement.executeUpdate();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }

        }

    }

    public void addMark(String mark) {

        if (JDBConnection.getInstance().getConnection() != null && !mark.equals("")) {

            String req = "INSERT INTO marks (mark_name) VALUES (?)";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, mark);

                JDBConnection.statement.executeUpdate();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }

        }

    }

    public void addUnit(String unit) {

        if (JDBConnection.getInstance().getConnection() != null && !unit.equals("")) {

            String req = "INSERT INTO units (unit_name) VALUES (?)";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, unit);

                JDBConnection.statement.executeUpdate();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }

        }

    }
}
