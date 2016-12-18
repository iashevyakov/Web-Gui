package ru.itis.inform.servlets.Delete;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.MarkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class DeleteMark extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private MarkService markService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mark = req.getParameter("mark");

        markService = ServiceFactory.getInstance().getMarkService();

        markService.deleteMark(mark);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duMark.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duMark.jsp");

        requestDispatcher.forward(req, resp);
    }
}