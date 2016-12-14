package ru.itis.inform.dao;

import ru.itis.inform.errors.Err;
import ru.itis.inform.models.Node;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;


public class NodeDaoImpl implements NodeDao {


    public void addNode(String unit, String node, String inventor_name, String inventor_country, String date) {

        if (JDBConnection.getInstance().getConnection() != null) {

            try {
                Date d = Date.valueOf(date);

                String req = "INSERT INTO nodes (node_name,unit_name,inventor_name,inventor_country,foundation) VALUES (?,?,?,?,?)";

                try {

                    JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                    JDBConnection.statement.setString(1, node);

                    JDBConnection.statement.setString(2, unit);

                    JDBConnection.statement.setString(3, inventor_name);

                    JDBConnection.statement.setString(4,inventor_country);

                    JDBConnection.statement.setDate(5,d);

                    JDBConnection.statement.executeUpdate();

                    Err.message="THE NODE IS ADDED!";
                } catch (SQLException sql) {

                    Err.message="CHECK YOUR ENTERED DATA!";

                }
            }
            catch (Exception e){
                Err.message="IMPOSSIBLE DATE!";
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

                Err.message="THE NODE IS DELETED!";

            } catch (SQLException sql) {

                Err.message="CHECK YOUR ENTERED DATA!";

            }

        }
        else{
            Err.message="SORRY! SERVER ERROR!";}
    }

    public LinkedList<Node> findAll() {
        return null;
    }
}
