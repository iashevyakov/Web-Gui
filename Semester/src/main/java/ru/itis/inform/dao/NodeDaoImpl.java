package ru.itis.inform.dao;

import java.sql.SQLException;

/**
 * Created by Иван on 05.11.2016.
 */
public class NodeDaoImpl implements NodeDao {


    public void addNode(String node, String unit) {

        if (JDBConnection.getInstance().getConnection() != null && !node.equals("")) {

            String req = "INSERT INTO nodes (node_name,unit_name) VALUES (?,?)";

            try {

                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, node);

                JDBConnection.statement.setString(2, unit);

                JDBConnection.statement.executeUpdate();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }

        }
    }
}
