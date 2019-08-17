package com.example.travelerpractise.FragmentForTreePage;

public class Budget {

    private String itemName;
    private String cost;
    private String costChage;

    public Budget(String itemName, String cost, String costChage) {
        this.itemName = itemName;
        this.cost = cost;
        this.costChage = costChage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCostChage() {
        return costChage;
    }

    public void setCostChage(String costChage) {
        this.costChage = costChage;
    }

    public Budget() {
    }
}
