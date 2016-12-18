package ru.itis.inform.servlets;
import ru.itis.inform.services.interfaces.TokenService;
import ru.itis.inform.services.implementations.TokenServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LogOut extends HttpServlet {
    //сервлет для выхода из аккаунта, удаляем cookie из БД(таблица cookies) и браузера и удаляем сессию,
    //дальше перенаправляем на страницу авторизациию
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("current_user")) {

                String token = cookie.getValue();
                //зануляем cookie браузера.
                cookie.setValue("");

                cookie.setPath("/");

                cookie.setMaxAge(0);

                TokenService tokenService = new TokenServiceImpl();
                //убираем cookie из БД.
                if (tokenService.findToken(token) != null) {

                    tokenService.deleteToken(token);

                }
                //добавляем пустой cookie в браузер(то есть удаляем его).
                resp.addCookie(cookie);

            }
        }
        //удаляем сессию данного пользователя.
        req.getSession().invalidate();
        //перенаправляем его на страницу авторизации.
        resp.sendRedirect("/login");

    }
}
