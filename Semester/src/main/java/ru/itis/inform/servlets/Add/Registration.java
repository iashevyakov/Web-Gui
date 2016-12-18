package ru.itis.inform.servlets.Add;

import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Registration extends HttpServlet {
    //севлет для добавления юзера администратором.
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
                Err.message="The User is registered!";
            } else {
                Err.message = "User with This Username already exists!";
            }

            req.setAttribute("current_user", session.getAttribute("current_user"));

            requestDispatcher = getServletContext().getRequestDispatcher("/registration.jsp");

            requestDispatcher.forward(req, resp);
        } else {

            Err.message="Check you name and password(>2 & >6)";

            req.setAttribute("current_user", session.getAttribute("current_user"));

            requestDispatcher = getServletContext().getRequestDispatcher("/registration.jsp");

            requestDispatcher.forward(req, resp);

        }
    }
}
