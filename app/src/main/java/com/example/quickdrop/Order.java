package com.example.quickdrop;

import java.util.List;

public class Order {
    private final int id;
    private final double price;
    private final double weight;
    private final int time;
    private final int productId;
    private final int status;

    public Order(){
        this.id = 0;
        this.price=0;
        this.weight = 0;
        this.time=0;
        this.productId=0;
        this.status=0;
    }

    public Order(int id, double price, double weight, int time, int productId, int status){
        this.id = id;
        this.price=price;
        this.weight = weight;
        this.time=time;
        this.productId=productId;
        this.status=status;
    }

    public int getId() {return id;}
    public double getPrice() {return price;}
    public double getWeight() {return weight;}
    public int getTime() {return time;}
    public int getProductId() {return productId;}
    public int getStatus() {return status;}

    public String getStringStatus(){
        if(status==0){
            return "сборка заказа";
        }
        if(status==1){
            return "в полёте";
        }
        return "получен";
    }
}
