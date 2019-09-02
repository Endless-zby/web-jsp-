package com.zby.entity;

public class Information {

    private String name;
    private String id;
    private String balance;
    public  Information(String name,String id,String balance){
        this.balance = balance;
        this.id = id;
        this.name = name;
    }
    public void Information(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}
