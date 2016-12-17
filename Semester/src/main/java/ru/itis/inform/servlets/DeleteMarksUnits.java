package ru.itis.inform.servlets;

import ru.itis.inform.dao.CountryDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.CountryService;
import ru.itis.inform.services.MarkUnitService;
import ru.itis.inform.services.TokenService;
import ru.itis.inform.services.TokenServiceImpl;
import ru.itis.inform.verifiers.CountryVerify;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class DeleteMarksUnits extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private MarkUnitService markUnitService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mark = req.getParameter("mark");

        String unit = req.getParameter("unit");

        markUnitService = ServiceFactory.getInstance().getMarkUnitService();

        markUnitService.deleteMarksUnits(mark,unit);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duMarkUnit.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duMarkUnit.jsp");

        requestDispatcher.forward(req, resp);
    }
}