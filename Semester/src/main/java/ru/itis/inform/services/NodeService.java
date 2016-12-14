package ru.itis.inform.services;

public interface NodeService {
    void deleteNode(String node);
    void addNode(String node, String unit,String inventor_name, String inventor_country, String date);
}
