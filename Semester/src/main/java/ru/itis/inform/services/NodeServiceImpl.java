package ru.itis.inform.services;

import ru.itis.inform.dao.NodeDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.verifiers.NodeVerify;


public class NodeServiceImpl implements NodeService {

    NodeDao nodeDao = DaoFactory.getInstance().getNodeDao();

    public void deleteNode(String node){

        if (node.length() > 2 && node.length()<16) {

            if (NodeVerify.checkNode(node) != null) {

                nodeDao = DaoFactory.getInstance().getNodeDao();

                nodeDao.deleteNode(node);

                Err.message= "THE NODE IS DELETED!";
            } else {

                Err.message="THE NODE DOESN'T EXISTS!";
            }
        }
        else{

            Err.message="A NAME OF NODE SHOULD BE MORE THAN 2 AND LESS THAN 16!";
        }
    }
}
