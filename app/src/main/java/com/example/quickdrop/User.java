package com.example.quickdrop;

public class User {

    private final String name;
    private final String surname;
    private final String lastName;
    private final String login;
    private final String order;
    private final String history;

    public User(){
        this.name = "";
        this.surname = "";
        this.lastName = "";
        this.login = "";
        this.order = "";
        this.history = "";
    }

    public User(String name, String surname, String lastName, String login, String order, String history){
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
        this.login = login;
        this.order = order;
        this.history = history;
    }

    public String getName() {return name;}

    public String getSurname() {return surname;}

    public String getHistory() {return history;}

    public String getOrder(){return order;}
}
