package ru.itis.inform.servlets.Add;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.MarkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class AddMark extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private MarkService markService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean ispetrol = false, isautomatic = false;

        String mark = req.getParameter("mark");

        String country = req.getParameter("country");

        String year = req.getParameter("year");

        String petrol = req.getParameter("petrol");

        String automatic = req.getParameter("automatic");

        if (petrol.equals("petrol")) {
            ispetrol = true;
        }
        if (automatic.equals("automatic")) {
            isautomatic = true;
        }

        markService = ServiceFactory.getInstance().getMarkService();

        markService.addMark(mark, country, year, ispetrol, isautomatic);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addMark.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addMark.jsp");

        requestDispatcher.forward(req, resp);
    }
}