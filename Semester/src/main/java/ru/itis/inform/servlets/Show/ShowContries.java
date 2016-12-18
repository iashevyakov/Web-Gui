package ru.itis.inform.servlets.Show;

import ru.itis.inform.dao.interfaces.CountryDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.models.Country;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.LinkedList;


public class ShowContries extends HttpServlet {
    //страница для показа всех кортежей таблицы Countries.
    private HttpSession session;

    private RequestDispatcher requestDispatcher;

    private LinkedList<Country> countries;

    private CountryDao countryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        countryDao = DaoFactory.getInstance().getCountryDao();

        countries = countryDao.findAll();

        req.setAttribute("countries",countries);
        //направляем на jsp(объект GUI), чтобы показать данные в браузере.
        requestDispatcher = getServletContext().getRequestDispatcher("/countries.jsp");

        requestDispatcher.forward(req, resp);

    }


}