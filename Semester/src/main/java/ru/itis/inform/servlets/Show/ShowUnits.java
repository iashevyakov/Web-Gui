package ru.itis.inform.servlets.Show;

import ru.itis.inform.dao.interfaces.MarksUnitsDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.models.Unit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.LinkedList;


public class ShowUnits extends HttpServlet {

    private HttpSession session;

    private RequestDispatcher requestDispatcher;

    private LinkedList<Unit> units;

    private MarksUnitsDao unitDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        unitDao = DaoFactory.getInstance().getMarksUnitsDao();

        units =  unitDao.findAllUnits();

        req.setAttribute("units",units);

        requestDispatcher = getServletContext().getRequestDispatcher("/units.jsp");

        requestDispatcher.forward(req, resp);

    }


}