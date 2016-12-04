package ru.itis.inform;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;


public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        JDBCImpl usersDao = new JDBCImpl();

        LinkedList<User> list = new LinkedList<User>();
        try {
            list = usersDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("users", list);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/students.jsp");

        requestDispatcher.forward(req,resp);
    }
}
