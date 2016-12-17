package ru.itis.inform.models;


import java.sql.Date;
import java.util.ArrayList;

public class Node {

    private String nodeName;

    public String getNodeName() {
        return nodeName;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getInventorName() {
        return inventorName;
    }

    public String getInventorCountry() {
        return inventorCountry;
    }

    public Date getFoundation() {
        return foundation;
    }

    private String unitName;

    private String inventorName;

    private String inventorCountry;

    private Date foundation;

    public Node(String nodeName, String unitName, String inventorName, String inventorCountry, Date foundation) {
        this.nodeName = nodeName;
        this.unitName = unitName;
        this.inventorName = inventorName;
        this.inventorCountry = inventorCountry;
        this.foundation = foundation;
    }


    public Node(String nodeName,String unitName){this.nodeName=nodeName;this.unitName=unitName;}

}
