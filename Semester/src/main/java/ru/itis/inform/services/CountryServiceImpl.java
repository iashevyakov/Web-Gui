package ru.itis.inform.services;

import ru.itis.inform.dao.CountryDao;
import ru.itis.inform.errors.Check;
import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.verifiers.CountryVerify;


import java.util.regex.Matcher;


public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao = DaoFactory.getInstance().getCountryDao();
    Matcher m;

    public void deleteCountry(String country) {
        boolean checkResult = false;
        checkResult = CountryVerify.check(country);
        if (checkResult){
            if(CountryVerify.checkCountry(country)!=null){

                countryDao.deleteCountry(country);
            }
            else{
                Err.message="WE DON'T DEAL WITH THIS COUNTRY!";
            }
        }
        else{
            Err.message="CHECK YOUR ENTERED DATA!";
        }

    }

    public void addCountry(String country, String continent, String president, boolean is_federation) {
        boolean checkResult = false;
        checkResult = CountryVerify.check(country, continent, president);
        if (checkResult) {
            if (CountryVerify.checkCountry(country)==null){
                countryDao.addCountry(country, continent, president, is_federation);
            }
            else{
                Err.message="WE ALREADY DEAL WITH COUNTRY CALLED BY THIS NAME!";
            }

        } else {
            Err.message = "CHECK YOUR ENTERED DATA!";
        }
    }
    public void updateCountry(String country, String newcountry, String continent, String president, boolean is_federation){
        boolean checkResult = false;
        checkResult = CountryVerify.check(newcountry, continent, president)&CountryVerify.check(country);
        if (checkResult) {
            if (CountryVerify.checkCountry(country)!=null){
                countryDao.updateCountry(country, newcountry, continent, president, is_federation);
            }
            else{
                Err.message="WE DON'T DEAL WITH THIS COUNTRY!";
            }

        } else {
            Err.message = "CHECK YOUR ENTERED DATA!";
        }
    }

}




