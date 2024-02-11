package com.codizcdp.inventorymanagement.model;

import java.io.Serializable;
import java.util.UUID;

public class Item implements Serializable {
    String ID, name, total_quantity, available_quantity, desc;
    public Item() {
    }

    public Item(String ID, String name, String total_quantity, String available_quantity, String desc) {
        this.ID = ID;
        this.name = name;
        this.total_quantity = total_quantity;
        this.available_quantity = available_quantity;
        this.desc = desc;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(String total_quantity) {
        this.total_quantity = total_quantity;
    }

    public String getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(String available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
