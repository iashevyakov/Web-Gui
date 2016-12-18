package ru.itis.inform.services.implementations;

import ru.itis.inform.dao.interfaces.MarksUnitsDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.services.interfaces.MarkUnitService;
import ru.itis.inform.verifiers.MarkVerify;
import ru.itis.inform.verifiers.MarksUnitsVerify;
import ru.itis.inform.verifiers.UnitVerify;

public class MarkUnitServiceImpl implements MarkUnitService {

    MarksUnitsDao marksUnitsDao = DaoFactory.getInstance().getMarksUnitsDao();
    //добавление соответствия между маркой и агрегатом в БД в таблицу marksunits, перед этим всевозможные проверки и геренация ошибок.
    public void addMarksUnits(String mark, String unit) {
        boolean checkResult = false;
        checkResult = MarkVerify.check(mark) & UnitVerify.check(unit);
        if (checkResult) {
            if (MarkVerify.checkMark(mark) != null && UnitVerify.checkUnit(unit) != null) {
                if (MarksUnitsVerify.CheckMarksUnits(mark,unit)==null) {
                    //все проверки пройдены, теперь непосредственно добавление строки в БД с помощью класса Dao(MarkUnitDao).
                    marksUnitsDao.addMarksUnits(mark, unit);
                }
                else{
                    Err.message = "THE CONFORMITY ALREADY EXISTS!";//соответствие уже установлено.
                }
            } else {
                Err.message = "WE HAVEN'T THIS MARK OR UNIT!";//в таблицах mark или unit нет строки, удовлетворяющей введенному значению.
                //То есть либо админ не добавил марку, либо не добавил агрегат в соответствующих таблицах.
            }
        } else {
            //данные не прошли проверку на правильность введения
            Err.message = "CHECK YOUR ENTERED DATA!";
        }
    }

    public void deleteMarksUnits(String mark, String unit) {
        boolean checkResult = false;
        //проверка на правильность введения в полях имени марки и агрегата.
        checkResult = MarkVerify.check(mark) & UnitVerify.check(unit);
        if (checkResult) {
            //проверка прошла
            if (MarkVerify.checkMark(mark)!=null&&UnitVerify.checkUnit(unit)!=null) {
                //если существуют такие марка и агрегат, то дальше проверяем на наличие соответствия у них.
                if (MarksUnitsVerify.CheckMarksUnits(mark, unit) != null) {
                    //такое соотвествие есть, удаляем его.
                    marksUnitsDao.deleteMarksUnits(mark, unit);
                } else {
                    //такого соответствия нет, говорим об этом пользователю:
                    Err.message = "THE CONFORMITY DOESN'T EXIST!";
                }
            }
            else{
                //нет таких марок и агрегатов в таблицах,которые соотвествуют введенным значениям,  сообщаем об этом :
                Err.message = "WE HAVEN'T THE MARK OR UNIT!";
            }
        } else {
            //проверка на правильность введения  не прошла
            Err.message = "CHECK YOUR ENTERED DATA!";
        }
    }
}
