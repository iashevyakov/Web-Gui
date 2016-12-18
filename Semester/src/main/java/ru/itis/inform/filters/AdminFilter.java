package ru.itis.inform.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;



import ru.itis.inform.models.User;
import ru.itis.inform.services.interfaces.TokenService;
import ru.itis.inform.services.implementations.TokenServiceImpl;
import ru.itis.inform.services.interfaces.UserService;
import ru.itis.inform.services.implementations.UserServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class AdminFilter implements Filter {
    //класс для безопасности
    //с помощью данного фильтра обычный пользователь не сможет получить права администратора,
    //так как не будет иметь возможности перейти по какой-то ссылке, на которую имеет достут только администратор.
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    //проверяем, есть ли cookie в браузере для нашего пользователя , если есть и если пользователь администатор, даем ему доступ к ссылке.
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("current_user")) {

                    TokenService tokenService = new TokenServiceImpl();

                    String id = tokenService.findToken(cookie.getValue());

                    UserService userService = new UserServiceImpl();

                    User user = userService.findId(id);

                    if (user==null ||!user.getIs_admin()) {


                        ((HttpServletResponse) servletResponse).sendRedirect("/home");

                        return;
                    }
                    else{

                        ((HttpServletRequest) servletRequest).getSession().setAttribute("current_user", user);

                    }
                }
            }
        }
        //если нету cookie для пользователя, то проверяем, есть ли для него сессия, если есть и если он админ, то даем ему доступ к ссылке.
        //иначе перенаправляем его на его домашнюю страницу.
        User user1 = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("current_user");
        if(user1==null||!user1.getIs_admin()){
            ((HttpServletResponse) servletResponse).sendRedirect("/home");
        }
        else {

            filterChain.doFilter(servletRequest, servletResponse);

            return;
        }


    }

    public void destroy() {

    }
}