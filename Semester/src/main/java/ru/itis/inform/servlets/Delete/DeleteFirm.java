package ru.itis.inform.servlets.Delete;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.FirmService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class DeleteFirm extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private FirmService firmService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firm = req.getParameter("firm");


        firmService = ServiceFactory.getInstance().getFirmService();

        firmService.deleteFirm(firm);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duFirm.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duFirm.jsp");

        requestDispatcher.forward(req, resp);
    }
}