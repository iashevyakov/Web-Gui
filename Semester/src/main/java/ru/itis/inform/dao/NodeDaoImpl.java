package ru.itis.inform.dao;

import ru.itis.inform.errors.Err;

import java.sql.SQLException;

/**
 * Created by Иван on 05.11.2016.
 */
public class NodeDaoImpl implements NodeDao {


    public void addNode(String node, String unit) {

        if (JDBConnection.getInstance().getConnection() != null) {

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
        else{Err.message="SORRY! SERVER ERROR!";}
    }
    public void deleteNode(String node) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "SELECT * FROM delete(?,'node') ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, node);

                JDBConnection.statement.executeQuery();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }

        }
        else{
            Err.message="SORRY! SERVER ERROR!";}
    }
}
