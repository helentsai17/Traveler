package com.example.travelerpractise;

public class DaysCard {


    private String daycard;
    private String countday;

    public DaysCard() {
    }

    public DaysCard( String daycard, String countday) {

        this.daycard = daycard;
        this.countday = countday;
    }


    public String getDaycard() {
        return daycard;
    }

    public void setDaycard(String daycard) {
        this.daycard = daycard;
    }

    public String getCountday() {
        return countday;
    }

    public void setCountday(String countday) {
        this.countday = countday;
    }
}
