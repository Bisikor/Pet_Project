package com.coder.currencyConverter.Models;

public class CourseModel {
     String currencyName;
     Double currencyPurchase;

    public CourseModel(String currencyName, Double currencyPurchase) {
        this.currencyName = currencyName;
        this.currencyPurchase = currencyPurchase;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getCurrencyPurchase() {
        return currencyPurchase;
    }

    public void setCurrencyPurchase(Double currencyPurchase) {
        this.currencyPurchase = currencyPurchase;
    }

}
