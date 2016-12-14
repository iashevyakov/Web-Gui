package ru.itis.inform.services;

import ru.itis.inform.dao.MarksUnitsDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.verifiers.MarkVerify;
import ru.itis.inform.verifiers.UnitVerify;


public class UnitServiceImpl implements UnitService {

    MarksUnitsDao marksUnitsDao = DaoFactory.getInstance().getMarksUnitsDao();

    public void deleteUnit(String unit) {
        boolean checkResult = false;
        checkResult = UnitVerify.check(unit);
        if (checkResult){
            if (UnitVerify.checkUnit(unit)!=null){
                marksUnitsDao.deleteUnit(unit);
            }
            else{
                Err.message="WE HAVEN'T THIS UNIT!";
            }
        }
        else{
            Err.message="CHECK YOUR ENTERED DATA!";
        }

    }

    public void addUnit(String unit, String inventor_name, String inventor_country, String date) {
        boolean checkResult = false;
        checkResult = UnitVerify.check(unit,inventor_name,inventor_country,date);
        if (checkResult) {
            if (UnitVerify.checkUnit(unit)==null){
                marksUnitsDao.addUnit(unit,inventor_name,inventor_country,date);
            }
            else{
                Err.message="WE ALREADY HAVE UNIT WITH THIS NAME!";
            }
        }
        else{
            Err.message="CHECK YOUR ENTERED DATA!";
        }
    }
}
