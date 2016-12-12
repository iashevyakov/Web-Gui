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


public class AddFirm extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private FirmService firmService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firm = req.getParameter("firm");

        String owner= req.getParameter("owner");

        String country = req.getParameter("country");

        String date = req.getParameter("date");

        firmService = ServiceFactory.getInstance().getFirmService();

        firmService.addFirm(firm,country,date,owner);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addFirm.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addFirm.jsp");

        requestDispatcher.forward(req, resp);
    }
}