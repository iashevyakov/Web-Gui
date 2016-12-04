package ru.itis.inform.servlets;

import jdk.nashorn.internal.ir.RuntimeNode;
import ru.itis.inform.dao.DetailDao;
import ru.itis.inform.dao.DetailDaoImpl;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.models.User;
import ru.itis.inform.services.DetailService;
import ru.itis.inform.services.DetailServiceImpl;
import ru.itis.inform.services.UserService;
import ru.itis.inform.services.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


public class Home extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;


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

        if (user.getIs_admin()) {

            String name = (String) req.getParameter("name");

            String uname = (String) req.getParameter("username");

            String password = (String) req.getParameter("password");

            String password2 = (String) req.getParameter("password2");

            String work = (String) req.getParameter("work");

            boolean profession;

            if (work.equals("workman")) {
                profession = true;
            } else {
                profession = false;
            }


            if (password.equals(password2) && name.length() >= 1 && uname.length() >= 3) {

                userService.add(name, uname, password, password2, false, profession);

                System.out.println(name + uname + password + password2 + profession);

                req.setAttribute("success", "User is successfully registered!");

                req.setAttribute("current_user", session.getAttribute("current_user"));

                requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

                requestDispatcher.forward(req, resp);
            } else {

                req.setAttribute("fail", "Check Your Password or Name");

                req.setAttribute("current_user", session.getAttribute("current_user"));

                requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

                requestDispatcher.forward(req, resp);

            }
        } else {

            if (!user.getIs_workman()) {

                String country = req.getParameter("country");

                String firm = req.getParameter("firm");

                String detail = req.getParameter("detail");

                String number = req.getParameter("number");

                String mark = req.getParameter("mark");

                String unit = req.getParameter("unit");

                String node = req.getParameter("node");

                System.out.println(country + firm + detail);

                if (country.length()>=3&& firm.length()>=3 && detail.length()>=3 && mark.length()>=3 && unit.length()>=3 && node.length()>=3 ) {

                    detailService.updateDetail(country, firm, detail, number, mark, unit, node);

                    req.setAttribute("success", "The Detail is Successfully Sold!");
                }
                else{
                    req.setAttribute("fail","Check you Entered Data!");
                }

                req.setAttribute("current_user", session.getAttribute("current_user"));

                requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

                requestDispatcher.forward(req, resp);
            } else {

                String country = req.getParameter("country");

                String firm = req.getParameter("firm");

                String detail = req.getParameter("detail");

                String number = req.getParameter("number");

                String mark = req.getParameter("mark");

                String unit = req.getParameter("unit");

                String node = req.getParameter("node");

                Boolean b = detailService.buyDetail(country, firm, detail, number, mark, unit, node);
                if (b) {

                    req.setAttribute("success", "The detail is Successfully Sold");

                    req.setAttribute("current_user", session.getAttribute("current_user"));

                    requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

                    requestDispatcher.forward(req, resp);
                } else {
                    req.setAttribute("fail", "The detail is Not Found! ");

                    req.setAttribute("current_user", session.getAttribute("current_user"));

                    requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");

                    requestDispatcher.forward(req, resp);


                }


            }

        }

    }

}