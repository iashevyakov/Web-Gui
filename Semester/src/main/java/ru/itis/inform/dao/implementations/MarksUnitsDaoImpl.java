package ru.itis.inform.dao.implementations;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.dao.interfaces.MarksUnitsDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.models.Mark;
import ru.itis.inform.models.MarkUnit;
import ru.itis.inform.models.Unit;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MarksUnitsDaoImpl implements MarksUnitsDao {

    private LinkedList<Mark> marks;
    private LinkedList<Unit> units;
    private LinkedList<MarkUnit> markUnits;

    public void addMarksUnits(String mark, String unit) {

        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "INSERT INTO marksunits (mark_name,unit_name) VALUES (?,?)";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, mark);

                JDBConnection.statement.setString(2, unit);

                JDBConnection.statement.executeUpdate();

                Err.message = "THE CONFORMITY IS ADDED!";//соответствие добавлено

            } catch (SQLException sql) {

                Err.message = "CHECK YOU ENTERED DATA!";//не соответствует CONSTRAINTS

            }

        } else {
            Err.message = "SORRY! SERVER ERROR!";
        }

    }

    public void deleteMarksUnits(String mark, String unit) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "DELETE FROM marksunits WHERE mark_name=? AND unit_name=?";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, mark);

                JDBConnection.statement.setString(2, unit);

                JDBConnection.statement.executeUpdate();

                Err.message = "THE CONFORMITY IS DELETED!";//соответствие удалено

            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";

            }

        } else {
            Err.message = "SORRY! SERVER ERROR!";
        }
    }

    public LinkedList<MarkUnit> findAll() {
        if (JDBConnection.getInstance().getConnection() != null) {

            markUnits = new LinkedList<MarkUnit>();

            String req = "SELECT * FROM marksunits_view";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    markUnits.add(new MarkUnit(resultSet.getString("mark_name"),resultSet.getString("unit_name")));

                }

                return markUnits;
            } catch (SQLException sql) {
                Err.message = "SORRY! SERVER ERROR!";
                return null;
            }
        } else {
            Err.message = "SORRY! SERVER ERROR!";
            return null;
        }
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

                Err.message = "THE MARK IS ADDED!";

            } catch (SQLException sql) {

                Err.message = "CHECK YOUR ENTERED DATA!";

            }

        } else {
            Err.message = "SORRY! SERVER ERROR!";
        }

    }

    public void addUnit(String unit, String inventor_name, String inventor_country, String foundation) {

        if (JDBConnection.getInstance().getConnection() != null) {

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
            } catch (Exception e) {
                Err.message = "IMPOSSIBLE DATE!";
            }

        } else {
            Err.message = "SORRY! SERVER ERROR!";
        }

    }

    public void deleteMark(String mark) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "SELECT * FROM delete(?,'mark') ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, mark);

                JDBConnection.statement.executeQuery();

                Err.message = "THE MARK IS DELETED!";

            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";

            }

        } else {
            Err.message = "SORRY! SERVER ERROR!";
        }
    }

    public LinkedList<Mark> findAllMarks() {
        if (JDBConnection.getInstance().getConnection() != null) {

            marks = new LinkedList<Mark>();

            String req = "SELECT * FROM marks_view";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    marks.add(new Mark(resultSet.getString("mark_name"), resultSet.getString("country_name"), resultSet.getInt("mark_year"), resultSet.getBoolean("isonpetrol"), resultSet.getBoolean("isautomatic")));

                }

                return marks;
            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";
                return null;
            }
        } else {
            Err.message = "SORRY! SERVER ERROR!";
            return null;
        }
    }

    public LinkedList<Unit> findAllUnits() {

        if (JDBConnection.getInstance().getConnection() != null) {

            units = new LinkedList<Unit>();

            String req = "SELECT * FROM units_view";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    units.add(new Unit(resultSet.getString("unit_name"), resultSet.getString("inventor_name"), resultSet.getString("inventor_country"), resultSet.getDate("foundation")));

                }

                return units;
            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";
                return null;
            }
        } else {
            Err.message = "SORRY! SERVER ERROR!";
            return null;
        }
    }

    public void deleteUnit(String unit) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "SELECT * FROM delete(?,'unit') ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, unit);

                JDBConnection.statement.executeQuery();

                Err.message = "THE UNIT IS DELETED!";

            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR";

            }

        } else {
            Err.message = "SORRY! SERVER ERROR!";
        }
    }
}
