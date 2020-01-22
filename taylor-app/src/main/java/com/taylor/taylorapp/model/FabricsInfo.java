package com.taylor.taylorapp.model;

import com.taylor.taylorapp.entities.Fabrics;

public class FabricsInfo {
    private String code;
    private String name;
    private double price;
 
    public FabricsInfo() {
    }
 
    public FabricsInfo(Fabrics fabrics) {
        this.code = fabrics.getCode();
        this.name = fabrics.getName();
        this.price = fabrics.getPrice();
    }
 
    // Using in JPA/Hibernate query
    public FabricsInfo(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
}
