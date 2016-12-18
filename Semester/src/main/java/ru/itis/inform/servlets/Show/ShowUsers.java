package ru.itis.inform.servlets.Show;

import ru.itis.inform.services.interfaces.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.models.User;

import java.sql.SQLException;
import java.util.LinkedList;


public class ShowUsers extends HttpServlet {

    private HttpSession session;

    private RequestDispatcher requestDispatcher;

    private LinkedList<User> users;

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        userService = ServiceFactory.getInstance().getUserService();

        try {
            users = userService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        req.setAttribute("users",users);

        requestDispatcher = getServletContext().getRequestDispatcher("/users.jsp");

        requestDispatcher.forward(req, resp);

    }


}