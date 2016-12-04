package ru.itis.inform.factories;

import ru.itis.inform.dao.*;
import ru.itis.inform.services.DetailService;
import ru.itis.inform.services.TokenService;
import ru.itis.inform.services.UserService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class DaoFactory {

    private static DaoFactory instance;
    private Properties properties;
    private UserDao userDao;
    private TokenDao tokenDao;
    private NodeDao nodeDao;
    private MarksUnitsDao marksUnitsDao;
    private FirmDao firmDao;
    private DetailDao detailDao;
    private CountryDao countryDao;


    private DaoFactory() {
        try {
            properties = new Properties();
            properties.load(
                    new FileInputStream("C:\\JavaProjects\\Semester\\src\\main\\resources\\dao.properties"));

            String userDaoClass = properties.getProperty("userDao.class");
            String tokenDaoClass = properties.getProperty("tokenDao.class");
            String detailDaoClass = properties.getProperty("detailDao.class");
            String nodeDaoClass = properties.getProperty("nodeDao.class");
            String marksUnitsDaoClass = properties.getProperty("marksUnitsDao.class");
            String firmDaoClass = properties.getProperty("firmDao.class");
            String countryDaoClass = properties.getProperty("countryDao.class");

            this.userDao = (UserDao) Class.forName(userDaoClass).newInstance();
            this.tokenDao = (TokenDao) Class.forName(tokenDaoClass).newInstance();
            this.detailDao = (DetailDao) Class.forName(detailDaoClass).newInstance();
            this.countryDao = (CountryDao) Class.forName(countryDaoClass).newInstance();
            this.firmDao = (FirmDao) Class.forName(firmDaoClass).newInstance();
            this.nodeDao = (NodeDao) Class.forName(nodeDaoClass).newInstance();
            this.marksUnitsDao = (MarksUnitsDao) Class.forName(marksUnitsDaoClass).newInstance();

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
        instance = new DaoFactory();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public TokenDao getTokenDao() {
        return tokenDao;
    }

    public NodeDao getNodeDao() {
        return nodeDao;
    }

    public MarksUnitsDao getMarksUnitsDao() {
        return marksUnitsDao;
    }

    public FirmDao getFirmDao() {
        return firmDao;
    }

    public DetailDao getDetailDao() {
        return detailDao;
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public static DaoFactory getInstance() {
        return instance;
    }
}
