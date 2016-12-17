package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.errors.Check;
import ru.itis.inform.errors.Err;
import ru.itis.inform.models.Country;
import ru.itis.inform.models.Detail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;

public class DetailVerify {

    private static Matcher m;

    public static Detail checkDetailAdmin(String firm, String detail, String node)
    {
        if (JDBConnection.getInstance().getConnection()!= null && !firm.equals("")) {

            String req = "SELECT * FROM details WHERE detail_name = ? AND firm_name = ? AND node_name = ? ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,detail);

                JDBConnection.statement.setString(2,firm);

                JDBConnection.statement.setString(3,node);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    return new Detail(resultSet.getString("detail_name"),resultSet.getString("firm_name"),resultSet.getString("node_name"),resultSet.getInt("quantity"));
                }
            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";

            }
            return null;


        }

        return null;
    }
    public static Detail checkDetail( String detail, String node)
    {
        if (JDBConnection.getInstance().getConnection()!= null) {

            String req = "SELECT * FROM details WHERE detail_name = ?  AND node_name = ? ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,detail);

                JDBConnection.statement.setString(2,node);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    return new Detail(resultSet.getString("detail_name"),resultSet.getString("firm_name"),resultSet.getString("node_name"),resultSet.getInt("quantity"));
                }
            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";

            }
            return null;


        }

        return null;
    }
    public static Detail checkDetail(String detail){
        if (JDBConnection.getInstance().getConnection()!= null) {

            String req = "SELECT * FROM details WHERE detail_name = ? ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,detail);


                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    return new Detail(resultSet.getString("detail_name"),resultSet.getString("firm_name"),resultSet.getString("node_name"),resultSet.getInt("quantity"));
                }
            } catch (SQLException sql) {

                Err.message = "SORRY! SERVER ERROR!";

            }
            return null;


        }

        return null;
    }

    // check - проверка на правильность введения данных из полей формы(правила - в классе Check пакета Error)
    public static boolean check(String detail, String number, String mark, String unit, String node) {
        boolean d = false, nu = false, ma = false, u = false, n = false;
        if (detail.equals("Detail") || number.equals("Quantity") || mark.equals("Mark") || unit.equals("Unit") || node.equals("Node")) {
            return false;
        }
        m = Check.parts.matcher(detail);
        if (m.matches()) {
            d = true;
        }
        m = Check.qCheck.matcher(number);
        if (m.matches()) {
            nu = true;
        }
        m = Check.parts.matcher(mark);
        if (m.matches()) {
            ma = true;
        }
        m = Check.parts.matcher(unit);
        if (m.matches()) {
            u = true;
        }
        m = Check.parts.matcher(node);
        if (m.matches()) {
            n = true;
        }
        if (d & nu & ma & u & n) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean check(String detail, String firm, String node) {
        boolean d = false, f = false, n = false;
        if (detail.equals("Detail") || firm.equals("Firm") || node.equals("Node")) {
            return false;
        }
        m = Check.parts.matcher(detail);
        if (m.matches()) {
            d = true;
        }
        m = Check.parts.matcher(node);
        if (m.matches()) {
            n = true;
        }
        m = Check.parts.matcher(firm);
        if (m.matches()) {
            f = true;
        }
        if(d&n&f){return true;}
        else{return false;}

    }
    public static boolean check(String detail){
        if (detail.equals("Detail"))
        {return false;}
        m = Check.parts.matcher(detail);
        if (m.matches()){
            return true;
        }
        else {
            return false;
        }

    }


}
