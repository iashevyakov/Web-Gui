package ru.itis.inform.services;

import ru.itis.inform.dao.NodeDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.verifiers.CountryVerify;
import ru.itis.inform.verifiers.FirmVerify;
import ru.itis.inform.verifiers.NodeVerify;
import ru.itis.inform.verifiers.UnitVerify;


public class NodeServiceImpl implements NodeService {

    NodeDao nodeDao = DaoFactory.getInstance().getNodeDao();


    public void deleteNode(String node) {

        boolean checkResult = false;
        checkResult = NodeVerify.check(node);
        if (checkResult) {

            if (NodeVerify.checkNode(node)!=null){
                nodeDao.deleteNode(node);
            }
            else{
                Err.message="WE HAVEN'T THIS NODE!";
            }

        }
        else{Err.message="CHECK YOUR ENTERED DATA!";}

    }

    public void addNode(String unit, String node, String inventor_name, String inventor_country, String date) {
        boolean checkResult = false;
        checkResult = NodeVerify.check(unit,node,inventor_name,inventor_country,date);
        if (checkResult){
            if (UnitVerify.checkUnit(unit)!=null){
                if(NodeVerify.checkNode(node)==null) {
                    nodeDao.addNode(unit, node, inventor_name, inventor_country, date);
                }
                else{
                    Err.message="WE ALREADY HAVE THIS NODE";
                }

            }
            else{
                Err.message="WE HAVEN'T THIS UNIT!";
            }
        }
        else{Err.message="CHECK YOUR ENTERED DATA!";}
    }
}
