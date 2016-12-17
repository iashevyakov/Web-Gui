package ru.itis.inform.services;

import ru.itis.inform.dao.*;
import ru.itis.inform.errors.Check;
import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.models.Firm;
import ru.itis.inform.verifiers.DetailVerify;
import ru.itis.inform.verifiers.FirmVerify;
import ru.itis.inform.verifiers.NodeVerify;

import java.sql.SQLException;
import java.util.regex.Matcher;

import static ru.itis.inform.verifiers.DetailVerify.check;
import static ru.itis.inform.verifiers.DetailVerify.checkDetail;
import static ru.itis.inform.verifiers.MarksUnitsVerify.CheckMarksUnits;
import static ru.itis.inform.verifiers.NodeVerify.checkNode;

public class DetailServiceImpl implements DetailService {

    private DetailDao detailDao = DaoFactory.getInstance().getDetailDao();

    //поставка детали на учет администратором, то есть её добавление для возможности поставщикам поставлять её и рабочим продавать.
    public void addDetail(String detail, String firm, String node) {
        boolean checkResult = false;
        //проверка на правильность введения данных в полях
        checkResult = check(detail, firm, node);
        if (checkResult){
            if(FirmVerify.checkFirm(firm)!=null){
                if (NodeVerify.checkNode(node)!=null)
                {
                    if (DetailVerify.checkDetail(detail)==null)
                    {detailDao.addDetail(detail, firm, node);}
                    else{Err.message="THE DETAIL IS ALREADY IN OUR CATALOG!";}
                }
                else{Err.message="WE DON'T BUY DETAILS OF THIS NODE";}
            }
            else{Err.message="WE DON'T DEAL WITH THIS FIRM!";}
        }
        else{Err.message="PARTS SHOULD BE NONSPACING CHARS!";}
    }
    //удаление детали из учета администратором, после этого поставщики и рабочие не смогут оперировать ею.
    //Чтобы заиметь такую возможнсть вновь, администратор должен добавить её на учет (методом addDetail).
    public void deleteDetail(String detail) {
        boolean checkResult = false;
        checkResult = check(detail);
        if (checkResult){
            if (DetailVerify.checkDetail(detail)!=null){
                detailDao.deleteDetail(detail);
            }
            else{
                Err.message="THERE ISN'T SUCH DETAIL IN OUR WAREHOUSE!";
            }
        }
        else{Err.message="DETAIL SHOULD BE LIKE NONSPACING CHARS!";}
    }
    //проверка перед отправкой детали на склад оптовиком
    public void sendDetail(String detail, String number, String mark, String unit, String node) {
        boolean checkResult = false;
        //переменная для проверки правильности ввдения данных в полях
        checkResult = check(detail, number, mark, unit, node);
        if (checkResult) {
            //проверку прошли, дальше проверка данных на БД.Если марка и агрегат совместимы, идем дальше, если нет, инициализируем ошибку.
            if (CheckMarksUnits(mark, unit) != null) {
                //марка и агрегат совместимы, дальше проверяем, есть ли узел, введенный в поле, в таблице nodes, который принадлежит введенному агрегату - checkNode(node, unit).
                if (checkNode(node, unit) != null) {
                    //узел нашелся, дальше проверяем, есть ли деталь, которая принадлежит введенному узлу - checkDetail(detail, node)
                    if (checkDetail(detail, node) != null) {
                        //такая деталь нашлась, пробуем её отправить, то есть изменить(прибавить) её количество
                        try {
                            detailDao.sendDetail(detail, number);

                        } catch (Exception e) {
                            //Если не вышло, инициализируем ошибку.
                            Err.message = "SORRY! SERVER ERROR!";
                        }

                    } else {
                        //такая деталь не нашлась, её нет на учете, инициализируем ошибку.
                        Err.message = "THERE ISN'T THE DETAIL IN OUR WAREHOUSE!";
                    }
                } else {
                    //нет узла, соответствующего введенному агрегату, создаем ошибку.
                    Err.message = "THERE AREN'T DETAILS OF THIS NODE IN OUR WAREHOUSE";
                }
            } else {
                //марка и агрегат не совместимы, создаем ошибку.
                Err.message = "THE MARK ANT THE UNIT AREN'T COMPATIBLE FOR OUR SHOP!";
            }
        } else {
            //данные изначально введены не по правилам, создаем ошибку.
            Err.message = "CHECK YOUR ENTERED DATA!";
        }


    }
    //всё то же самое , что и для sendDetail, только количество детали минусуем, так как мы её продаём.
    public void sellDetail(String detail, String number, String mark, String unit, String node) {
        boolean checkResult = false;
        checkResult = check(detail, number, mark, unit, node);
        if (checkResult) {
            if (CheckMarksUnits(mark, unit) != null) {
                if (checkNode(node, unit) != null) {
                    if (checkDetail(detail, node) != null) {

                        detailDao.sellDetail(detail, number);

                    } else {
                        Err.message = "THERE ISN'T THE DETAIL IN OUR WAREHOUSE!";
                    }
                } else {
                    Err.message = "THERE AREN'T DETAILS OF THIS NODE IN OUR WAREHOUSE";
                }
            } else {
                Err.message = "THE MARK ANT THE UNIT AREN'T COMPATIBLE FOR OUR SHOP!";
            }
        } else {
            Err.message = "CHECK YOUR ENTERED DATA!";
        }


    }


}
