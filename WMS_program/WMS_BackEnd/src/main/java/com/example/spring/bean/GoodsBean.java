package com.example.spring.bean;

import com.example.spring.model.Goods;

public class GoodsBean extends Goods {
    private int inventory_id;

    public GoodsBean(){
        super();
        inventory_id = 0;
    }

    public GoodsBean(int id, String name, int quantity, double price, String description, int inventory_id){
        super(id, name, quantity, price, description);
        this.inventory_id = inventory_id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }
}
