package com.ssg.proyectorecuperacion.model;

public class BookDetail {

    private String title;
    private String description;
    private String first_publish_date;

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        if (description == null) return "No description";

        return description;
    }

    public String getYear() {
        if (first_publish_date != null) {
            return first_publish_date;
        }
        return "-";
    }
}
