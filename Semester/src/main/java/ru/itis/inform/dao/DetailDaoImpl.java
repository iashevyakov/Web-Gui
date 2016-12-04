package ru.itis.inform.dao;

import java.sql.SQLException;

public class DetailDaoImpl implements DetailDao {

    public void updateDetail(String detail,String number) {

        if (JDBConnection.getInstance().getConnection() != null && !detail.equals("")) {

            int num = Integer.parseInt(number);

            String req = "UPDATE details SET quantity = quantity + ? WHERE detail_name= ? ;";


            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setInt(1, num);

                JDBConnection.statement.setString(2, detail);

                JDBConnection.statement.executeUpdate();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }

        }

    }
    public void addDetail(String detail,String firm,String number, String node)
    {
        if (JDBConnection.getInstance().getConnection() != null && !firm.equals("")) {

            String req = "INSERT INTO details (detail_name,firm_name,quantity,node_name) VALUES (?,?,?,?)";

            int num=Integer.parseInt(number);

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, detail);

                JDBConnection.statement.setString(2, firm);

                JDBConnection.statement.setInt(3, num);

                JDBConnection.statement.setString(4, node);

                JDBConnection.statement.executeUpdate();

            } catch (SQLException sql) {

                sql.printStackTrace();

            }

        }
    }
    public void buyDetail(String detail, String number) {


        if (JDBConnection.getInstance().getConnection() != null && !detail.equals("")) {

        int num = Integer.parseInt(number);

        String req = "UPDATE details SET quantity = quantity - ? WHERE detail_name= ? ;";


        try {
            JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

            JDBConnection.statement.setInt(1, num);

            JDBConnection.statement.setString(2, detail);

            JDBConnection.statement.executeUpdate();

        } catch (SQLException sql) {

            sql.printStackTrace();

        }

    }

}



}
