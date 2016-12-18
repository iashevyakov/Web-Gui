package ru.itis.inform.filters;
import ru.itis.inform.models.User;
import ru.itis.inform.services.interfaces.TokenService;
import ru.itis.inform.services.implementations.TokenServiceImpl;
import ru.itis.inform.services.interfaces.UserService;
import ru.itis.inform.services.implementations.UserServiceImpl;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }
    //этот фильтр проверяет, был ли пользователь когда-либо авторизован в данном браузере,
    //если да, то со страницы авторизацию его перебрости сразу же на домашнюю страницу.
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    //проверяем, есть ли cookie в браузере для данного пользователя, если есть, то сразу же перенаправялем его н домашнюю страницу /home.
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("current_user")) {

                    TokenService tokenService = new TokenServiceImpl();

                    String id = tokenService.findToken(cookie.getValue());

                    UserService userService = new UserServiceImpl();

                    User user = userService.findId(id);
                    //куки нашлись, перенаправляем на /home.
                    if (user != null) {


                        ((HttpServletRequest) servletRequest).getSession().setAttribute("current_user", user);


                        ((HttpServletResponse) servletResponse).sendRedirect("/home");


                        return;
                    }
                }
            }
        }
        //куки не нашлись, ищем в сессии.
        //Если сессия нашлась, перенаправляем, если нет - оставляет его на /login.
        if (((HttpServletRequest) servletRequest).getSession().getAttribute("current_user") != null) {


                  ((HttpServletResponse) servletResponse).sendRedirect("/home");


            return;
        }

         else {

            filterChain.doFilter(servletRequest, servletResponse);

            return;
        }
    }

    public void destroy() {

    }
}