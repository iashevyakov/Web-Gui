package ru.itis.inform.servlets.Show;

import ru.itis.inform.dao.interfaces.MarksUnitsDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.models.MarkUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.LinkedList;


public class ShowMarksUnits extends HttpServlet {

    private HttpSession session;

    private RequestDispatcher requestDispatcher;

    private LinkedList<MarkUnit> marksunits;

    private MarksUnitsDao marksUnitsDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        marksUnitsDao = DaoFactory.getInstance().getMarksUnitsDao();

        marksunits = marksUnitsDao.findAll();

        req.setAttribute("markunit", marksunits);

        requestDispatcher = getServletContext().getRequestDispatcher("/marksunits.jsp");

        requestDispatcher.forward(req, resp);

    }


}