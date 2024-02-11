package com.codizcdp.inventorymanagement.model;

public class Student {
    private String name, class_name, roll_no, collage_code;

    public Student() {

    }

    public Student(String name, String class_name, String roll_no, String collage_code) {
        this.name = name;
        this.class_name = class_name;
        this.roll_no = roll_no;
        this.collage_code = collage_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getCollage_code() {
        return collage_code;
    }

    public void setCollage_code(String collage_code) {
        this.collage_code = collage_code;
    }
}
