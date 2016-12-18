package ru.itis.inform.models;


public class DetailsView {
    //представление details_view
    private String markName;

    private String nodeName;

    private String detailName;

    private String firmName;

    private int quantity;

    private String unitName;


    public DetailsView(String markName,String unitName,String nodeName,String detailName,String firmName,int quantity){this.firmName=firmName;this.detailName=detailName;this.nodeName=nodeName;this.quantity=quantity;this.markName=markName;this.unitName=unitName;}


    public String getFirmName() {
        return firmName;
    }
    public String getDetailName() {
        return detailName;
    }
    public String getMarkName() {
        return markName;
    }
    public String getUnitName() {
        return unitName;
    }
    public String getNodeName() {
        return nodeName;
    }
    public int getQuantity() {
        return quantity;
    }




}
