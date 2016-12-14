package ru.itis.inform.servlets;

import ru.itis.inform.dao.CountryDao;
import ru.itis.inform.dao.DetailDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.models.Country;
import ru.itis.inform.models.Detail;
import ru.itis.inform.models.DetailsView;
import ru.itis.inform.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.models.User;

import java.sql.SQLException;
import java.util.LinkedList;


public class ShowDetails extends HttpServlet {

    private HttpSession session;

    private RequestDispatcher requestDispatcher;

    private LinkedList<DetailsView> details;

    private DetailDao detailDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        detailDao = DaoFactory.getInstance().getDetailDao();

        details = detailDao.findAll();

        req.setAttribute("details",details);

        requestDispatcher = getServletContext().getRequestDispatcher("/details.jsp");

        requestDispatcher.forward(req, resp);

    }


}