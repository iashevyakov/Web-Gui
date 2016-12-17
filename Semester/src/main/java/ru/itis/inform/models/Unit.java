package ru.itis.inform.models;

import java.sql.Date;
import java.util.ArrayList;

public class Unit {

    private String unitName;

    private String inventorName;

    public String getInventorName() {
        return inventorName;
    }

    public String getInventorCountry() {
        return inventorCountry;
    }

    public Date getFoundation() {
        return foundation;
    }

    private String inventorCountry;

    private Date foundation;

    public Unit(String unitName, String inventorName, String inventorCountry, Date foundation) {
        this.unitName = unitName;
        this.inventorName = inventorName;
        this.inventorCountry = inventorCountry;
        this.foundation = foundation;
    }



    public Unit(String unitName){this.unitName=unitName;}


    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
