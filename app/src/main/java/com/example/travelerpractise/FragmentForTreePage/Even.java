package com.example.travelerpractise.FragmentForTreePage;

public class Even {

    private String Even;
    private String Address;
    private String phoneNumber;
    private String OpenHour;
    private String Cost;
    private String Web;
    private String Image;

    public Even(String even, String address, String phoneNumber, String openHour, String cost, String web, String image) {
        Even = even;
        Address = address;
        this.phoneNumber = phoneNumber;
        OpenHour = openHour;
        Cost = cost;
        Web = web;
        Image = image;

    }

    public String getEven() {
        return Even;
    }

    public void setEven(String even) {
        Even = even;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOpenHour() {
        return OpenHour;
    }

    public void setOpenHour(String openHour) {
        OpenHour = openHour;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getWeb() {
        return Web;
    }

    public void setWeb(String web) {
        Web = web;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }



    public Even() {
    }
}







