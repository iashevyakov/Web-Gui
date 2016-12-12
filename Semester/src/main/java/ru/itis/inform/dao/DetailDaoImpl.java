package ru.itis.inform.dao;



import ru.itis.inform.errors.Check;
import ru.itis.inform.errors.Err;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.regex.Matcher;

public class DetailDaoImpl implements DetailDao {

    private Matcher m ;

    public void sendDetail(String detail, String number) {

            if (JDBConnection.getInstance().getConnection() != null) {

                int num = Integer.parseInt(number);

                String req = "SELECT * FROM update_det_plus(?,?);";


                try {
                    JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                    JDBConnection.statement.setString(1, detail);

                    JDBConnection.statement.setInt(2, num);

                    JDBConnection.statement.executeQuery();

                    Err.message="THE DETAIL IS SENT!";

                } catch (SQLException sql) {

                    Err.message="SORRY! SERVER ERROR!";

                }

            }
            else{Err.message="SORRY! SERVER ERROR!";}


    }
    public void addDetail(String detail,String firm, String node) {
        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "INSERT INTO details (detail_name,firm_name,node_name) VALUES (?,?,?)";


            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, detail);

                JDBConnection.statement.setString(2, firm);

                JDBConnection.statement.setString(3, node);

                JDBConnection.statement.executeUpdate();

                Err.message="THE DETAIL IS ADDED";

            } catch (SQLException sql) {

                Err.message="CHECK YOUR ENTERED DATA! THE NAME OF DETAIL SHOULD BE IN (2,16)!";

            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}
    }
    public void sellDetail(String detail, String number)  {



        if (JDBConnection.getInstance().getConnection() != null) {

            int num = Integer.parseInt(number);

            String req = "SELECT  * FROM update_det_minus(?,?);";


            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, detail);

                JDBConnection.statement.setInt(2, num);

                JDBConnection.statement.executeQuery();

                Err.message="THE DETAIL IS SOLD!";

            } catch (SQLException sql) {

                Err.message="WE HAVEN'T SO MANY PIECE OF THE DETAIL! TRY TO SELL LESS!";

            }


    }
    else{Err.message="SORRY! SERVER ERROR!";}


}

    public void deleteDetail(String detail) {

        if (JDBConnection.getInstance().getConnection() != null) {

            String req = "SELECT * FROM delete(?,'detail') ";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                JDBConnection.statement.setString(1, detail);

                JDBConnection.statement.executeQuery();

                Err.message="THE DETAIL IS DELETED!";

            } catch (SQLException sql) {

                Err.message="SORRY! SERVER ERROR!";

            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}
    }



}
