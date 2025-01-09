package com.example.spring.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Goods {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;

    public Goods(){}

    public Goods(int id, String name, int quantity, double price, String description){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void set_stand_description(String name, LocalDateTime date, String extra){
        description = "创建时间：" + date + ";  货物描述：" + extra + "  --创建者：" + name;
    }
}
