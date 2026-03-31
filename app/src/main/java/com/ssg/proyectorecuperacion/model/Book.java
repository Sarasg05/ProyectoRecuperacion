package com.ssg.proyectorecuperacion.model;

import java.util.List;

public class Book {
    private String title;
    private List<String> author_name;
    private int first_publish_year;
    
    public String getTitle(){ return title; }
    public String getAuthor(){
        if (author_name != null && !author_name.isEmpty()){
            return author_name.get(0);
        }
        return "Unknown";
    }
    public String getYear(){
        return String.valueOf(first_publish_year);
    }
    public String getCategory(){
        return "Ficción"; // valor fijo
    }
    public String getStatus(){
        return "Pendiente"; // valor fijo
    }
}
