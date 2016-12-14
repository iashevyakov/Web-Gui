package ru.itis.inform.servlets;

import ru.itis.inform.dao.CountryDao;
import ru.itis.inform.dao.MarksUnitsDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.models.Country;
import ru.itis.inform.models.Mark;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.LinkedList;


public class ShowMarks extends HttpServlet {

    private HttpSession session;

    private RequestDispatcher requestDispatcher;

    private LinkedList<Mark> marks;

    private MarksUnitsDao markDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        markDao = DaoFactory.getInstance().getMarksUnitsDao();

        marks = markDao.findAllMarks();

        req.setAttribute("marks",marks);

        requestDispatcher = getServletContext().getRequestDispatcher("/marks.jsp");

        requestDispatcher.forward(req, resp);

    }


}