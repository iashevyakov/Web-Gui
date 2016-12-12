package ru.itis.inform.servlets;

import ru.itis.inform.dao.CountryDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.CountryService;
import ru.itis.inform.services.TokenService;
import ru.itis.inform.services.TokenServiceImpl;
import ru.itis.inform.verifiers.CountryVerify;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class AddCountry extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private CountryService countryService ;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String country= req.getParameter("country");

        String continent = req.getParameter("continent");

        String president = req.getParameter("president");

        String isfederation = req.getParameter("isfederation");

        countryService = ServiceFactory.getInstance().getCountryService();

        if (isfederation.equals("federation")){
            countryService.addCountry(country,continent,president,true);
        }
        else{
            countryService.addCountry(country,continent,president,false);
        }

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addCountry.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addCountry.jsp");

        requestDispatcher.forward(req, resp);
    }
}