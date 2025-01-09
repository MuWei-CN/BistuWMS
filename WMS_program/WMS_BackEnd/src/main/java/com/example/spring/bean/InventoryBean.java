package com.example.spring.bean;

public class InventoryBean {
    private String creator_name;
    private int id;
    private String message;

    public InventoryBean(){};

    public InventoryBean(String creatorName, int id, String message) {
        this.creator_name = creatorName;
        this.id = id;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }
}
