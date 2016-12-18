package ru.itis.inform.servlets.Add;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.FirmService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


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