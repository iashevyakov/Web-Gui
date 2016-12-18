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

public class HomeFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    //фильтр для доступа к домашней странице, дает доступ к ссылке /home , если пользователь ранее авторизвался, или для него есть cookie или сессия.

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //проверяем, есть ли пользователь в cookie браузера.
        //если да, даем ему доступ к /home, если нет, направляем на /login.
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("current_user")) {

                    TokenService tokenService = new TokenServiceImpl();

                    String id = tokenService.findToken(cookie.getValue());

                    UserService userService = new UserServiceImpl();

                    User user = userService.findId(id);

                    if (user == null) {
                        //cookie для этого юзера нет, направляем его на /login.
                        ((HttpServletResponse) servletResponse).sendRedirect("/login");

                        return;
                    }
                    else{

                        ((HttpServletRequest) servletRequest).getSession().setAttribute("current_user", user);

                    }
                }
            }
        }

            //ищем сессию для пользователя, есть нет её, то направляем на /login, если есть, даем доступ на /home.
            if(((HttpServletRequest) servletRequest).getSession().getAttribute("current_user")==null){
                ((HttpServletResponse) servletResponse).sendRedirect("/login");
            }
            else {

                filterChain.doFilter(servletRequest, servletResponse);

                return;
            }


    }

    public void destroy() {

    }
}
