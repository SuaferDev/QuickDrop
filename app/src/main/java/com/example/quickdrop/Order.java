package com.example.quickdrop;

import java.util.List;

public class Order {
    private final int id;
    private final double price;
    private final double weight;
    private final int time;
    private final int productId;
    private final int status;
    private final int idDrone;

    private final double latitude;
    private final double longitude;

    public Order(){
        this.id = 0;
        this.price=0;
        this.weight = 0;
        this.time=0;
        this.productId=0;
        this.status=0;
        this.idDrone = 0;
        this.latitude = 0;
        this.longitude = 0;
    }

    public Order(int id, double price, double weight, int time, int productId, int status, int idDrone, String adress, double latitude, double longitude){
        this.id = id;
        this.price=price;
        this.weight = weight;
        this.time=time;
        this.productId=productId;
        this.status=status;
        this.idDrone = idDrone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {return id;}
    public double getPrice() {return price;}
    public double getWeight() {return weight;}
    public int getTime() {return time;}
    public int getProductId() {return productId;}
    public int getStatus() {return status;}
    public int getIdDrone() {return idDrone;}

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
