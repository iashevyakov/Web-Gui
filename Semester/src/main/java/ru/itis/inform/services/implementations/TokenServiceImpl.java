package ru.itis.inform.services.implementations;
import ru.itis.inform.dao.interfaces.TokenDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.services.interfaces.TokenService;


public class TokenServiceImpl implements TokenService {

    TokenDao tokenDao = null;

    public TokenServiceImpl() {
        tokenDao = DaoFactory.getInstance().getTokenDao();
    }

    public void addToken(String id, String token) {
        tokenDao.addToken(id,token);
    }

    public void updateToken(String id, String token) {
        tokenDao.updateToken(id,token);
    }

    public void deleteToken(String token) {
        tokenDao.deleteToken(token);
    }

    public String findToken(String token) {
        return tokenDao.findIdByToken(token);
    }
}