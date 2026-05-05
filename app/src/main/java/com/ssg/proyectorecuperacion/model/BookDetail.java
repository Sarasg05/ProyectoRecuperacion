package com.ssg.proyectorecuperacion.model;

public class BookDetail {

    private String title;
    private String description;

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        if (description == null) return "No description";

        return description;
    }
}
