package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.models.Firm;
import ru.itis.inform.models.Node;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Иван on 05.11.2016.
 */
public class NodeVerify {

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

}
