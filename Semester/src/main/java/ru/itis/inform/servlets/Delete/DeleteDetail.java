package ru.itis.inform.servlets.Delete;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.DetailService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class DeleteDetail extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private DetailService detailService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String detailname = req.getParameter("detail");


        detailService = ServiceFactory.getInstance().getDetailService();

        detailService.deleteDetail(detailname);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duDetail.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duDetail.jsp");

        requestDispatcher.forward(req, resp);
    }
}