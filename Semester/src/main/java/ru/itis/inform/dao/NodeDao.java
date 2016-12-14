package ru.itis.inform.dao;

import ru.itis.inform.models.Node;

import java.util.LinkedList;

/**
 * Created by Иван on 05.11.2016.
 */
public interface NodeDao {

    void addNode(String unit, String node, String inventor_name, String inventor_country, String date);
    void deleteNode(String node);
    LinkedList<Node> findAll();

}
