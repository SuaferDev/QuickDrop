package com.example.quickdrop;

public class UserData {
    private static final UserData instance = new UserData();

    private String login;

    private UserData(){}

    public static UserData getInstance(){
        return instance;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
