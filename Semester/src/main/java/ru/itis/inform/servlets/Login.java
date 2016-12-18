package ru.itis.inform.servlets;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.models.User;
import ru.itis.inform.services.interfaces.TokenService;
import ru.itis.inform.services.interfaces.UserService;
import ru.itis.inform.utils.MD5Util;
import ru.itis.inform.utils.Token;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class Login extends HttpServlet {
    //страница авторизации для пользователей.

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
        //ищем пользователя по введенному им логину.
        User currentUser = userService.find(login);

        if (currentUser != null) {
            //нашелся
            if (MD5Util.md5Apache(password).equals(currentUser.getPassword())) {
            //пароль введен правильно, создаем для него cookie в браузере и перенаправляем на домашнюю страницу.
                HttpSession session = req.getSession();

                session.setAttribute("current_user", currentUser);

                req.setAttribute("current_user",currentUser);

                String token = Token.getToken();

                Cookie cookie = new Cookie("current_user",token);

                cookie.setMaxAge(30*24*60*60);
                //добавление cookie для данного пользователя в браузер.
                resp.addCookie(cookie);

                tokenService = ServiceFactory.getInstance().getTokenService();

                System.out.println(currentUser.getId()+token);

                tokenService.addToken(""+currentUser.getId(), token);
                //перенаправление на домашнюю страницу
                resp.sendRedirect("/home");

            } else {
                //пароль введен неверно, создаем ошибку и отправляем пользователю.
                req.setAttribute("incorrect_password", "Enter your password Again!");

                req.setAttribute("login",login);

                requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");

                requestDispatcher.forward(req, resp);

            }

        } else {
            //нет в бД юзера с данным аккаунтом, создаем ошибку и отпправляем пользователю.
            req.setAttribute("user not found","Account with this Username Doesn't Exist!" );

            requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");

            requestDispatcher.forward(req, resp);

        }
    }
}