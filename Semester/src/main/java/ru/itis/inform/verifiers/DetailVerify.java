package ru.itis.inform.verifiers;

import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.models.Country;
import ru.itis.inform.models.Detail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailVerify {

    public static Detail checkDetail(String firm, String detail, String node)
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

                    return new Detail(resultSet.getString("detail_name"),resultSet.getString("firm_name"),resultSet.getString("node_name"));
                }
            } catch (SQLException sql) {

                sql.printStackTrace();

            }
            return null;


        }

        return null;
    }

}
