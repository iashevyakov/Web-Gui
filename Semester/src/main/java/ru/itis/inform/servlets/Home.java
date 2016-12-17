package ru.itis.inform.servlets;

import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.models.User;
import ru.itis.inform.services.DetailService;
import ru.itis.inform.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class Home extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    //класс, отвечающий за домашнию страницу любоого типа пользователя
    //севлеты принимают данные из форм, вызывают методы классов Service, где содержится вся логика.
    //Service в свою очередь вызывают методы классов Dao, где происходит работа с БД.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DetailService detailService = ServiceFactory.getInstance().getDetailService();

        UserService userService = ServiceFactory.getInstance().getUserService();

        User user = (User) session.getAttribute("current_user");


        //если форму отправляет оптовик, принимать значения полей и вызывать метод у DetailService sendDetail ()
        //для проверки введенных данных, и дальнейшей работы с БД (в данном случае, добавление опр-го кол-ва деталей на склад, так как пользователь - оптовик.)
            if (!user.getIs_workman()) {

                String detail = req.getParameter("detail");

                String number = req.getParameter("number");

                String mark = req.getParameter("mark");

                String unit = req.getParameter("unit");

                String node = req.getParameter("node");

                detailService.sendDetail(detail, number, mark, unit, node);

                req.setAttribute("current_user", session.getAttribute("current_user"));

                requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

                requestDispatcher.forward(req, resp);
            }
            //если пользователь - рабочий, то вызываем метод sellDetail(), где произойдет обработка данных и дальнейшая работа с БД,
            //в данном случае - уменьшение кол-ва детали на складе(рабочий)
            else {

                String detail = req.getParameter("detail");

                String number = req.getParameter("number");

                String mark = req.getParameter("mark");

                String unit = req.getParameter("unit");

                String node = req.getParameter("node");

                detailService.sellDetail(detail, number, mark, unit, node);

                req.setAttribute("current_user", session.getAttribute("current_user"));

                requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

                requestDispatcher.forward(req, resp);


            }



    }

}