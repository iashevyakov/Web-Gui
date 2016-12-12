package ru.itis.inform.servlets;

import ru.itis.inform.dao.CountryDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.DetailService;
import ru.itis.inform.services.DetailServiceImpl;
import ru.itis.inform.services.TokenService;
import ru.itis.inform.services.TokenServiceImpl;
import ru.itis.inform.verifiers.CountryVerify;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


public class AddDetail extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private DetailService detailService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firm = req.getParameter("firm");

        String node = req.getParameter("node");

        String detail = req.getParameter("detail");

        detailService = ServiceFactory.getInstance().getDetailService();

        detailService.addDetail(detail,firm,node);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addDetail.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addDetail.jsp");

        requestDispatcher.forward(req, resp);
    }
}