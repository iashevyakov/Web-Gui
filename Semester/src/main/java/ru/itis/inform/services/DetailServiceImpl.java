package ru.itis.inform.services;

import ru.itis.inform.dao.*;
import ru.itis.inform.errors.Error;
import ru.itis.inform.factories.DaoFactory;

import java.sql.SQLException;

import static ru.itis.inform.verifiers.CountryVerify.checkCountry;
import static ru.itis.inform.verifiers.DetailVerify.checkDetail;
import static ru.itis.inform.verifiers.FirmVerify.checkFirm;
import static ru.itis.inform.verifiers.MarksUnitsVerify.CheckMarksUnits;
import static ru.itis.inform.verifiers.NodeVerify.checkNode;

public class DetailServiceImpl implements DetailService {

    private DetailDao detailDao = DaoFactory.getInstance().getDetailDao();

    private CountryDao countryDao = DaoFactory.getInstance().getCountryDao();

    private FirmDao firmDao = DaoFactory.getInstance().getFirmDao();

    private NodeDao nodeDao = DaoFactory.getInstance().getNodeDao();

    private MarksUnitsDao marksUnitsDao = DaoFactory.getInstance().getMarksUnitsDao();

    public void updateDetail(String country, String firm, String detail, String number, String mark, String unit, String node) {

        if (checkCountry(country) != null) {
            if (checkFirm(firm, country) != null) {
                if (CheckMarksUnits(mark, unit) != null) {
                    if (checkNode(node, unit) != null)
                        if (checkDetail(firm, detail, node) != null) {
                            detailDao.updateDetail(detail, number);
                        } else {
                            detailDao.addDetail(detail, firm, number, node);
                        }
                    else {
                        nodeDao.addNode(node, unit);
                        detailDao.addDetail(detail, firm, number, node);
                    }
                } else {
                    marksUnitsDao.addMarksUnits(mark, unit);
                    nodeDao.addNode(node, unit);
                    detailDao.addDetail(detail, firm, number, node);
                }

            } else {

                firmDao.addFirm(firm, country);

                if (CheckMarksUnits(mark, unit) != null) {
                    if (checkNode(node, unit) != null)
                        if (checkDetail(firm, detail, node) != null) {
                            detailDao.updateDetail(detail, number);
                        } else {
                            detailDao.addDetail(detail, firm, number, node);
                        }
                    else {
                        nodeDao.addNode(node, unit);
                        detailDao.addDetail(detail, firm, number, node);
                    }
                } else {
                    marksUnitsDao.addMarksUnits(mark, unit);
                    nodeDao.addNode(node, unit);
                    detailDao.addDetail(detail, firm, number, node);
                }


            }
        } else {
            countryDao.addCountry(country);
            firmDao.addFirm(firm, country);

            if (CheckMarksUnits(mark, unit) != null) {
                if (checkNode(node, unit) != null)
                    if (checkDetail(firm, detail, node) != null) {
                        detailDao.updateDetail(detail, number);
                    } else {
                        detailDao.addDetail(detail, firm, number, node);
                    }
                else {
                    nodeDao.addNode(node, unit);
                    detailDao.addDetail(detail, firm, number, node);
                }
            } else {
                marksUnitsDao.addMarksUnits(mark, unit);
                nodeDao.addNode(node, unit);
                detailDao.addDetail(detail, firm, number, node);
            }

        }

    }

    public boolean buyDetail(String country, String firm, String detail, String number, String mark, String unit, String node) {

        if (checkCountry(country) != null) {
            if (checkFirm(firm, country) != null) {
                if (CheckMarksUnits(mark, unit) != null) {
                    if (checkNode(node, unit) != null) {
                        if (checkDetail(firm, detail, node) != null) {
                            try {
                                detailDao.buyDetail(detail, number);
                                return true;
                            } catch (Exception e) {
                                return false;
                            }

                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                else{
                        return false;
                    }

                }
                else{return false;}

            }
            else{return false;}
        }
    }
