package com.example.quickdrop;

public class Product {
    private final int id;
    private final String name;
    private final double price;
    private final double weight;

    public Product(){
        this.id = 0;
        this.name="";
        this.price = 0;
        this.weight=0;
    }

    public Product(int id, String name, double price, double weight){
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public double getPrice() {return price;}
    public double getWeight() {return weight;}
}
