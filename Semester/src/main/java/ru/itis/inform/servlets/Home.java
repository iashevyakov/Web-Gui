package ru.itis.inform.servlets;

import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.models.User;
import ru.itis.inform.services.DetailService;
import ru.itis.inform.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class Home extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Err.message="";

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DetailService detailService = ServiceFactory.getInstance().getDetailService();

        UserService userService = ServiceFactory.getInstance().getUserService();

        User user = (User) session.getAttribute("current_user");

        if (user.getIs_admin()) {


        } else {

            if (!user.getIs_workman()) {

                String detail = req.getParameter("detail");

                String number = req.getParameter("number");

                String mark = req.getParameter("mark");

                String unit = req.getParameter("unit");

                String node = req.getParameter("node");

                detailService.sendDetail(detail, number, mark, unit, node);

                req.setAttribute("current_user", session.getAttribute("current_user"));

                requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

                requestDispatcher.forward(req, resp);
            } else {

                String detail = req.getParameter("detail");

                String number = req.getParameter("number");

                String mark = req.getParameter("mark");

                String unit = req.getParameter("unit");

                String node = req.getParameter("node");

                detailService.sellDetail(detail, number, mark, unit, node);

                req.setAttribute("current_user", session.getAttribute("current_user"));

                requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

                requestDispatcher.forward(req, resp);


            }

        }

    }

}