package ru.itis.inform.servlets.Add;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.MarkUnitService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class AddMarksUnits extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private MarkUnitService markUnitService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mark = req.getParameter("mark");

        String unit = req.getParameter("unit");

        markUnitService = ServiceFactory.getInstance().getMarkUnitService();

        markUnitService.addMarksUnits(mark,unit);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addMarkUnit.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addMarkUnit.jsp");

        requestDispatcher.forward(req, resp);
    }
}