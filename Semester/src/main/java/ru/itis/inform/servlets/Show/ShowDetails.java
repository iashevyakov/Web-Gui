package ru.itis.inform.servlets.Show;

import ru.itis.inform.dao.interfaces.DetailDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.models.DetailsView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.LinkedList;


public class ShowDetails extends HttpServlet {
    //сервлет для показа каталога в веб-браузере.

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
        //достаем данные из БД с помощью представлений.
        details = detailDao.findAll();

        req.setAttribute("details",details);
        //выдаем данные на вывод в браузере.
        requestDispatcher = getServletContext().getRequestDispatcher("/details.jsp");

        requestDispatcher.forward(req, resp);

    }


}