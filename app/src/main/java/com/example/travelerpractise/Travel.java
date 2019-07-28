package com.example.travelerpractise;

public class Travel {

    private String continent;
    private String country;
    private String city;
    private String travelDate;
    private String returnDate;

    public Travel() {
    }

    public Travel(String continent, String country, String city, String travelDate, String returnDate) {
        this.continent = continent;
        this.country = country;
        this.city = city;
        this.travelDate = travelDate;
        this.returnDate = returnDate;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String retrunDate) {
        this.returnDate = retrunDate;
    }
}
