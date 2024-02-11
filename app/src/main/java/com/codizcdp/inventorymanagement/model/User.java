package com.codizcdp.inventorymanagement.model;

public class User {
    private String email, type, collage_code;


    public User() {
    }

    public User(String email, String type, String collage_code) {
        this.email = email;
        this.type = type;
        this.collage_code = collage_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollage_code() {
        return collage_code;
    }

    public void setCollage_code(String collage_code) {
        this.collage_code = collage_code;
    }
}
