package ru.itis.inform.servlets.Delete;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class DeleteUser extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");

        userService = ServiceFactory.getInstance().getUserService();

        userService.delete(username);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duUser.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duUser.jsp");

        requestDispatcher.forward(req, resp);
    }
}