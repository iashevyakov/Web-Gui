package ru.itis.inform.factories;

import ru.itis.inform.errors.Err;
import ru.itis.inform.services.interfaces.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//иньъекция зависимостей, то есть средство для легкой подмены реализации интерфейса.
public class ServiceFactory {

    private static ServiceFactory instance;
    private Properties properties;
    private UserService userService;
    private TokenService tokenService;
    private DetailService detailService;
    private NodeService nodeService;
    private CountryService countryService;
    private MarkService markService;
    private UnitService unitService;
    private FirmService firmService;
    private MarkUnitService markUnitService;

    private ServiceFactory() {
        try {
            properties = new Properties();
            properties.load(
                    new FileInputStream("C:\\JavaProjects\\Semester\\src\\main\\resources\\service.properties"));

            String userServiceClass = properties.getProperty("userService.class");
            String tokenServiceClass = properties.getProperty("tokenService.class");
            String detailServiceClass = properties.getProperty("detailService.class");
            String nodeServiceClass = properties.getProperty("nodeService.class");
            String countryServiceClass = properties.getProperty("countryService.class");
            String markServiceClass = properties.getProperty("markService.class");
            String unitServiceClass = properties.getProperty("unitService.class");
            String firmServiceClass = properties.getProperty("firmService.class");
            String markUnitServiceClass = properties.getProperty("markUnitService.class");
            this.userService = (UserService) Class.forName(userServiceClass).newInstance();
            this.tokenService = (TokenService) Class.forName(tokenServiceClass).newInstance();
            this.detailService = (DetailService) Class.forName(detailServiceClass).newInstance();
            this.nodeService = (NodeService) Class.forName(nodeServiceClass).newInstance();
            this.countryService = (CountryService) Class.forName(countryServiceClass).newInstance();
            this.markService = (MarkService) Class.forName(markServiceClass).newInstance();
            this.unitService = (UnitService) Class.forName(unitServiceClass).newInstance();
            this.firmService = (FirmService) Class.forName(firmServiceClass).newInstance();
            this.markUnitService = (MarkUnitService) Class.forName(markUnitServiceClass).newInstance();


        } catch (IOException e) {
            Err.message = "SORRY! SERVER ERROR!";
        } catch (ClassNotFoundException e) {
            Err.message = "SORRY! SERVER ERROR!";
        } catch (IllegalAccessException e) {
            Err.message = "SORRY! SERVER ERROR!";
        } catch (InstantiationException e) {
            Err.message = "SORRY! SERVER ERROR!";
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

    public NodeService getNodeService() {
        return nodeService;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public MarkService getMarkService() {
        return markService;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public FirmService getFirmService() {
        return firmService;
    }

    public MarkUnitService getMarkUnitService() {
        return markUnitService;
    }
}
