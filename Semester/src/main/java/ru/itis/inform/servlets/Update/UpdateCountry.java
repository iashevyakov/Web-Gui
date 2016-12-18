package ru.itis.inform.servlets.Update;


import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.CountryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateCountry extends HttpServlet{
    //сервлет для обновления кортежей таблицы countries.
    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private CountryService countryService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //принимаем данные формы
        String country = (String) session.getAttribute("country");

        String newcountry = req.getParameter("newcountry");

        String continent = req.getParameter("continent");

        String president = req.getParameter("president");

        String type = req.getParameter("isfederation");

        countryService = ServiceFactory.getInstance().getCountryService();

        if (type.equals("federation")){
            countryService.updateCountry(country,newcountry,continent,president,true);
        }
        else{
            countryService.updateCountry(country,newcountry,continent,president,false);
        }

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/updateCountry.jsp");

        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/updateCountry.jsp");

        requestDispatcher.forward(req, resp);
    }

}
