package ru.itis.inform.servlets.Delete;

import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.CountryService;
import ru.itis.inform.verifiers.CountryVerify;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class DeleteCountry extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private CountryService countryService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String country = req.getParameter("country");

        String option = req.getParameter("option");

        if (option.equals("delete")) {

            countryService = ServiceFactory.getInstance().getCountryService();

            countryService.deleteCountry(country);

            req.setAttribute("current_user", session.getAttribute("current_user"));

            requestDispatcher = getServletContext().getRequestDispatcher("/duCountry.jsp");

            requestDispatcher.forward(req, resp);
        }
        else{
            if (CountryVerify.checkCountry(country)!=null&&CountryVerify.check(country)) {
                session.setAttribute("country", country);
                resp.sendRedirect("/updateCountry");
            }
            else{

                Err.message = "WE DON'T DEAL WITH THIS COUNTRY!";

                req.setAttribute("current_user", session.getAttribute("current_user"));

                requestDispatcher = getServletContext().getRequestDispatcher("/duCountry.jsp");

                requestDispatcher.forward(req, resp);

                Err.message = "";
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duCountry.jsp");

        requestDispatcher.forward(req, resp);
    }
}