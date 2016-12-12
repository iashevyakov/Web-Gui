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


    public void addDetail(String detail, String firm, String node) {
        boolean checkResult = false;
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

    public void sendDetail(String detail, String number, String mark, String unit, String node) {
        boolean checkResult = false;
        checkResult = check(detail, number, mark, unit, node);
        if (checkResult) {
            if (CheckMarksUnits(mark, unit) != null) {
                if (checkNode(node, unit) != null) {
                    if (checkDetail(detail, node) != null) {
                        try {
                            detailDao.sendDetail(detail, number);

                        } catch (Exception e) {
                            Err.message = "CHECK YOU ENTERED DATA!";
                        }

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
            Err.message = "QUANTITY SHOULD BE POSITIVE NUMBER, PARTS SHOULD BE NONSPACING CHARS!";
        }


    }

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
            Err.message = "QUANTITY SHOULD BE POSITIVE NUMBER, PARTS SHOULD BE NONSPACING CHARS!";
        }


    }


}
