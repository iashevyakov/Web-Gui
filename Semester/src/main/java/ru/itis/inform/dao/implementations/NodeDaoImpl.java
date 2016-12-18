package ru.itis.inform.dao.implementations;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.dao.interfaces.NodeDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.models.Node;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class NodeDaoImpl implements NodeDao {
    //класс для работы с nodes в БД.
    LinkedList<Node> nodes;
    //добавляем node в БД.
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
    //удалеяем узел из БД.
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
    //достаем все узлы из БД с помощью представления nodes_view
    public LinkedList<Node> findAll() {

        if (JDBConnection.getInstance().getConnection() != null) {

            nodes = new LinkedList<Node>();

            String req = "SELECT * FROM nodes_view";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while(resultSet.next()){

                    nodes.add(new Node(resultSet.getString("node_name"),resultSet.getString("unit_name"),resultSet.getString("inventor_name"),resultSet.getString("inventor_country"),resultSet.getDate("foundation")));

                }

                return nodes;
            } catch (SQLException sql) {

                Err.message="SORRY! SERVER ERROR!";
                return null;
            }
        }
        else{Err.message="SORRY! SERVER ERROR!";return null;}
    }
}

