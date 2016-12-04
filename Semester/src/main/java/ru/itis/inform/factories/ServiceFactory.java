package ru.itis.inform.factories;

import ru.itis.inform.services.DetailService;
import ru.itis.inform.services.TokenService;
import ru.itis.inform.services.UserService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServiceFactory {

    private static ServiceFactory instance;
    private Properties properties;
    private UserService userService;
    private TokenService tokenService;
    private DetailService detailService;

    private ServiceFactory() {
        try {
            properties = new Properties();
            properties.load(
                    new FileInputStream("C:\\JavaProjects\\Semester\\src\\main\\resources\\service.properties"));

            String userServiceClass = properties.getProperty("userService.class");
            String tokenServiceClass = properties.getProperty("tokenService.class");
            String detailServiceClass = properties.getProperty("detailService.class");


            this.userService = (UserService) Class.forName(userServiceClass).newInstance();
            this.tokenService = (TokenService) Class.forName(tokenServiceClass).newInstance();
            this.detailService = (DetailService) Class.forName(detailServiceClass).newInstance();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException();
        }
    }

    static {
        instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return this.userService;
    }

    public TokenService getTokenService() {
        return tokenService;
    }

    public DetailService getDetailService() {
        return detailService;
    }
}
