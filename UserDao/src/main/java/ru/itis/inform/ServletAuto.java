package ru.itis.inform;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Иван on 19.09.2016.
 */
public class ServletAuto extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        JDBCAutoImpl usersDao = new JDBCAutoImpl();

        LinkedList<Auto> list = new LinkedList<Auto>();
        try {
            list = usersDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("autos", list);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/auto.jsp");

        requestDispatcher.forward(req,resp);
    }
}
