package ru.itis.inform.services;

import ru.itis.inform.dao.FirmDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.verifiers.CountryVerify;
import ru.itis.inform.verifiers.FirmVerify;


public class FirmServiceImpl implements FirmService {

    FirmDao firmDao = DaoFactory.getInstance().getFirmDao();

    public void addFirm(String firm, String country,String date, String owner){
        boolean checkResult = false;
        checkResult = FirmVerify.check(firm, country, owner,date);
        if (checkResult){
            if (CountryVerify.checkCountry(country)!=null){
                if(FirmVerify.checkFirm(firm)==null) {
                    firmDao.addFirm(firm, country, date, owner);
                }
                else{
                    Err.message="WE ALREADY DEAL WITH FIRM CALLED BY THIS NAME";
                }

            }
            else{
                Err.message="WE DON'T DEAL WITH THIS COUNTRY!";
            }
        }
        else{Err.message="CHECK YOUR ENTERED DATA!";}
    }
    public void deleteFirm(String firm){
        boolean checkResult = false;
        checkResult = FirmVerify.check(firm);
        if (checkResult) {

            if (FirmVerify.checkFirm(firm)!=null){
                firmDao.deleteFirm(firm);
            }
            else{
                Err.message="WE DON'T DEAL WITH THIS FIRM!";
            }

        }
        else{Err.message="FIRM SHOULD BE LIKE NONSPACING CHARS!";}
    }
}
