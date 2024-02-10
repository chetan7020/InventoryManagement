package com.codizcdp.inventorymanagement.model;

public class Admin {
    private String name, collage_code;

    public Admin() {

    }

    public Admin(String name, String collage_code) {
        this.name = name;
        this.collage_code = collage_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollage_code() {
        return collage_code;
    }

    public void setCollage_code(String collage_code) {
        this.collage_code = collage_code;
    }
}
