package com.ssg.proyectorecuperacion.model;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private String title;
    private List<String> author_name;
    private int first_publish_year;

    private int cover_i;

    private String key;
    
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

    public String getCoverUrl(){
        if (cover_i != 0){
            return "https://covers.openlibrary.org/b/id/" + cover_i + "-L.jpg";
        }
        return null;
    }

    public String getWorkId(){

        return key.replace("/works/","");
    }
}
