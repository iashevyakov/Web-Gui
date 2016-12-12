package ru.itis.inform.services;

import ru.itis.inform.dao.MarksUnitsDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.verifiers.MarkVerify;

/**
 * Created by Иван on 11.12.2016.
 */
public class MarkServiceImpl implements MarkService {

    MarksUnitsDao marksUnitsDao = DaoFactory.getInstance().getMarksUnitsDao();

    public void deleteMark(String mark) {
        boolean checkResult = false;
        checkResult = MarkVerify.check(mark);
        if (checkResult){
            if (MarkVerify.checkMark(mark)!=null){
                marksUnitsDao.deleteMark(mark);
            }
            else{
                Err.message="WE HAVEN'T THIS MARK!";
            }
        }
        else{
            Err.message="PARTS SHOULD BE NON-SPACING CHARS!";
        }
    }

    public void addMark(String mark, String country, String year, boolean petrol, boolean automatic) {
        boolean checkResult = false;
        checkResult = MarkVerify.check(mark, country, year);
        if (checkResult) {
            if (MarkVerify.checkMark(mark)==null){
                int y = Integer.parseInt(year);
                marksUnitsDao.addMark(mark,country,y,petrol,automatic);
            }
            else{
                Err.message="WE ALREADY HAVE MARK WITH THIS NAME";
            }
        }
        else{
            Err.message="CHECK YOUR ENTERED DATA!";
        }
    }
}
