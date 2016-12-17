package ru.itis.inform.services;

import ru.itis.inform.dao.MarksUnitsDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.verifiers.MarkVerify;
import ru.itis.inform.verifiers.MarksUnitsVerify;
import ru.itis.inform.verifiers.UnitVerify;

public class MarkUnitServiceImpl implements MarkUnitService {

    MarksUnitsDao marksUnitsDao = DaoFactory.getInstance().getMarksUnitsDao();

    public void addMarksUnits(String mark, String unit) {
        boolean checkResult = false;
        checkResult = MarkVerify.check(mark) & UnitVerify.check(unit);
        if (checkResult) {
            if (MarkVerify.checkMark(mark) != null && UnitVerify.checkUnit(unit) != null) {
                if (MarksUnitsVerify.CheckMarksUnits(mark,unit)==null) {
                    marksUnitsDao.addMarksUnits(mark, unit);
                }
                else{
                    Err.message = "THE CONFORMITY ALREADY EXISTS!";//такая строка уже есть
                }
            } else {
                Err.message = "WE HAVEN'T THIS MARK OR UNIT!";
            }
        } else {
            Err.message = "CHECK YOUR ENTERED DATA!";
        }
    }

    public void deleteMarksUnits(String mark, String unit) {
        boolean checkResult = false;
        checkResult = MarkVerify.check(mark) & UnitVerify.check(unit);
        if (checkResult) {
            if (MarkVerify.checkMark(mark)!=null&&UnitVerify.checkUnit(unit)!=null) {
                if (MarksUnitsVerify.CheckMarksUnits(mark, unit) != null) {
                    marksUnitsDao.deleteMarksUnits(mark, unit);
                } else {
                    Err.message = "THE CONFORMITY DOESN'T EXIST!";
                }
            }
            else{
                Err.message = "WE HAVEN'T THE MARK OR UNIT!";
            }
        } else {
            Err.message = "CHECK YOUR ENTERED DATA!";
        }
    }
}
