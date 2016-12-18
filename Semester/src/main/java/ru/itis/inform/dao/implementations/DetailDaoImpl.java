package ru.itis.inform.dao.implementations;
import ru.itis.inform.dao.JDBConnection;
import ru.itis.inform.dao.interfaces.DetailDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.models.DetailsView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.regex.Matcher;

public class DetailDaoImpl implements DetailDao {

    private Matcher m ;

    private LinkedList<DetailsView> catalog;
    //отправление определенного количества деталей оптовиком на склад
    public void sendDetail(String detail, String number) {
            //плюсуем детали на складе
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
    //непосредственно добавление детали в БД.

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

                Err.message="CHECK YOUR ENTERED DATA!";

            }

        }
        else{Err.message="SORRY! SERVER ERROR!";}
    }
    //продажа детали рабочим
    public void sellDetail(String detail, String number)  {
            //минусуем детали на складе
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
                //при недостатке такой детали на складе
                Err.message="WE HAVEN'T SO MANY PIECE OF THE DETAIL! TRY TO SELL LESS!";

            }


    }
    else{Err.message="SORRY! SERVER ERROR!";}


}
//удаление детали из БД. detail_name - PK, поэтому для удаления детали достаточно указать в форме лишь его.
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
    //чтение таблицы details(from CRUD), использование details_view.
    public LinkedList<DetailsView> findAll() {

        if (JDBConnection.getInstance().getConnection() != null) {

            catalog = new LinkedList<DetailsView>();

            String req = "SELECT * FROM details_view";

            try {
                JDBConnection.statement = JDBConnection.getInstance().getConnection().prepareStatement(req);

                ResultSet resultSet = JDBConnection.statement.executeQuery();

                while(resultSet.next()){

                    catalog.add(new DetailsView(resultSet.getString("mark_name"),resultSet.getString("unit_name"),resultSet.getString("node_name"),resultSet.getString("detail_name"),resultSet.getString("firm_name"),resultSet.getInt("quantity")));

                }

                return catalog;
            } catch (SQLException sql) {

                Err.message="SORRY! SERVER ERROR!";
                return null;
            }

        }
        else{Err.message="SORRY! SERVER ERROR!";return null;}
    }




}
