package ru.itis.inform.servlets;

import ru.itis.inform.dao.CountryDao;
import ru.itis.inform.dao.FirmDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.models.Country;
import ru.itis.inform.models.Firm;
import ru.itis.inform.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.models.User;

import java.sql.SQLException;
import java.util.LinkedList;


public class ShowFirms extends HttpServlet {

    private HttpSession session;

    private RequestDispatcher requestDispatcher;

    private LinkedList<Firm> firms;

    private FirmDao firmDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        firmDao = DaoFactory.getInstance().getFirmDao();

        firms = firmDao.findAll();

        req.setAttribute("firms",firms);

        requestDispatcher = getServletContext().getRequestDispatcher("/firms.jsp");

        requestDispatcher.forward(req, resp);

    }


}