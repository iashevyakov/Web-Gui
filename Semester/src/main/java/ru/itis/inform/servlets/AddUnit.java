package ru.itis.inform.servlets;

import ru.itis.inform.dao.CountryDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.*;
import ru.itis.inform.verifiers.CountryVerify;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


public class AddUnit extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private UnitService unitService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String unit = req.getParameter("unit");

        String inventor_name = req.getParameter("inventor_name");

        String inventor_country = req.getParameter("inventor_country");

        String date = req.getParameter("date");


        unitService = ServiceFactory.getInstance().getUnitService();

        unitService.addUnit(unit, inventor_name, inventor_country,date);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addUnit.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addUnit.jsp");

        requestDispatcher.forward(req, resp);
    }
}