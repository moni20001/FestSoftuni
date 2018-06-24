package com.example.simeon.europeanroulettegame.entities.models;

public class User {
    private String username;
    private Integer balance;

    public User(String username) {
        this.username = username;
        this.balance = 1000;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
