package ru.itis.inform;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/studentAdd.jsp");

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain");

        String name = req.getParameter("name");

        String city = req.getParameter("city");

        int age = Integer.parseInt(req.getParameter("age"));

        User user = null;

        try {

            user = new User(name, city, age);

            if (user.getCity().length()!=0)
            {

                user.setIdU(user.hashCode());

                System.out.println(city+name+age+user.getIdU());

            }

            try {

                JDBCImpl jdbc = new JDBCImpl();

                jdbc.save(user);

                int idU=user.getIdU();

                resp.getWriter().println("Name: "+name+", City: "+city+", Age: "+age+", Your ID: "+idU);


            } catch (Exception e) {
                req.setAttribute("error_c_u","Error create user!");
            }
        } catch (Exception e) {
            req.setAttribute("error", "Error!");
        }
    }
}
