package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.errors.Check;
import ru.itis.inform.models.Firm;
import ru.itis.inform.models.Node;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;

public class NodeVerify {

    private static Matcher m;

    public static Node checkNode(String node, String unit){

        if (JDBConnection.getInstance().getConnection()!= null && !node.equals("")) {

            String req = "SELECT * FROM nodes WHERE node_name = ? AND unit_name = ? ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,node);

                JDBConnection.statement.setString(2,unit);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    return new Node(resultSet.getString("node_name"),resultSet.getString("unit_name"));
                }
            } catch (SQLException sql) {

                sql.printStackTrace();

            }
            return null;


        }

        return null;

    }
    public static Node checkNode(String node){

        if (JDBConnection.getInstance().getConnection()!= null && !node.equals("")) {

            String req = "SELECT * FROM nodes WHERE node_name = ? ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1,node);



                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while (resultSet.next()) {

                    return new Node(resultSet.getString("node_name"),resultSet.getString("unit_name"));
                }
            } catch (SQLException sql) {

                sql.printStackTrace();

            }
            return null;


        }

        return null;

    }
    public static boolean check(String unit, String node, String inventor_name, String inventor_country, String date){
        if (unit.equals("Unit") ||node.equals("Node") || inventor_name.equals("Name of Inventor)") || inventor_country.equals("Country of Inventor") || date.equals("Foundation:YYYY-MM-DD")) {
            return false;
        }

        boolean u = false,n=false, in = false, ic = false, d = false;
        m = Check.parts.matcher(unit);
        if (m.matches()){u=true;}
        m = Check.parts.matcher(node);
        if(m.matches()){n=true;}
        m=Check.parts.matcher(inventor_name);
        if (m.matches()){in=true;}
        m=Check.parts.matcher(inventor_country);
        if (m.matches()){ic=true;}
        m=Check.dateCheck.matcher(date);
        if(m.matches()){d=true;}
        if(u&in&ic&d&n){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean check(String node){
        if (node.equals("Node")){
            return false;
        }
        m = Check.parts.matcher(node);
        return m.matches();
    }

}
