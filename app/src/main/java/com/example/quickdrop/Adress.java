package com.example.quickdrop;

public class Adress {

    private final String street;
    private final String house;

    public Adress() {
        this.street = "";
        this.house = "";
    }

    public Adress(String street, String house) {
        this.street = street;
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }
}
