package ru.itis.inform.servlets;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Registration extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/registration.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = ServiceFactory.getInstance().getUserService();

        String name = (String) req.getParameter("name");

        String uname = (String) req.getParameter("username");

        String password = (String) req.getParameter("password");

        String work = (String) req.getParameter("work");

        if (name.equals("Name"))
        {name="d";}
        if(uname.equals("Username"))
        {uname="d";}

        boolean profession;

        if (work.equals("workman")) {
            profession = true;
        } else {
            profession = false;
        }


        if ( name.length() > 2 && uname.length() > 2 && password.length() > 6) {

            Boolean b = userService.add(name, uname, password, false, profession);

            System.out.println(name + uname + password + profession);
            if (b) {
                req.setAttribute("success", "User is successfully registered!");
            } else {
                req.setAttribute("fail", "User with THIS Username already exists!");
            }

            req.setAttribute("current_user", session.getAttribute("current_user"));

            requestDispatcher = getServletContext().getRequestDispatcher("/registration.jsp");

            requestDispatcher.forward(req, resp);
        } else {

            req.setAttribute("fail", "Name and Username Should Be More than 2, password - more than 6");

            req.setAttribute("current_user", session.getAttribute("current_user"));

            requestDispatcher = getServletContext().getRequestDispatcher("/registration.jsp");

            requestDispatcher.forward(req, resp);

        }
    }
}
