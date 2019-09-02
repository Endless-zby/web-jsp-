package com.zby.entity;

public class Account {
    private String frommoney ;
    private String tomoney;
    private String transferaccountsmoney ;

    public Account(String frommoney,String tomoney,String transferaccountsmoney){
        this.frommoney = frommoney;
        this.tomoney = tomoney;
        this.transferaccountsmoney = transferaccountsmoney;
    }
    public Account(){

    }
    public String getFrommoney() {
        return frommoney;
    }

    public void setFrommoney(String frommoney) {
        this.frommoney = frommoney;
    }

    public String getTomoney() {
        return tomoney;
    }

    public void setTomoney(String tomoney) {
        this.tomoney = tomoney;
    }

    public String getMoney() {
        return transferaccountsmoney;
    }

    public void setMoney(String money) {
        this.transferaccountsmoney = transferaccountsmoney;
    }




}
