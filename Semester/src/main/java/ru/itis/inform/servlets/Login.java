package ru.itis.inform.servlets;

import ru.itis.inform.errors.Error;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.messages.Message;
import ru.itis.inform.models.User;
import ru.itis.inform.services.TokenService;
import ru.itis.inform.services.TokenServiceImpl;
import ru.itis.inform.services.UserService;
import ru.itis.inform.services.UserServiceImpl;
import ru.itis.inform.utils.MD5Util;
import ru.itis.inform.utils.Token;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class Login extends HttpServlet {

    HttpSession session;

    RequestDispatcher requestDispatcher;

    Cookie cookie;

    UserService userService;

    TokenService tokenService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        String login = req.getParameter("login");

        String password = req.getParameter("password");

        userService = ServiceFactory.getInstance().getUserService();

        User currentUser = userService.find(login);

        if (currentUser != null) {

            if (MD5Util.md5Apache(password).equals(currentUser.getPassword())) {

                HttpSession session = req.getSession();

                session.setAttribute("current_user", currentUser);

                req.setAttribute("current_user",currentUser);

                String token = Token.getToken();

                Cookie cookie = new Cookie("current_user",token);

                cookie.setMaxAge(30*24*60*60);

                resp.addCookie(cookie);

                tokenService = ServiceFactory.getInstance().getTokenService();

                System.out.println(currentUser.getId()+token);

                tokenService.addToken(""+currentUser.getId(), token);

                resp.sendRedirect("/home");

            } else {

                req.setAttribute("incorrect_password", "Enter your password Again!");

                req.setAttribute("login",login);

                requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");

                requestDispatcher.forward(req, resp);

            }

        } else {

            req.setAttribute("user not found","Account with this Username Doesn't Exist!" );

            requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");

            requestDispatcher.forward(req, resp);

        }
    }
}