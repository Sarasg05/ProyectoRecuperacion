package com.ssg.proyectorecuperacion.model;

public class Book {
    private String title;
    private String author;
    private String year;
    private String category;
    private String status;

    public Book(String title, String author, String year, String category, String status){
        this.title=title;
        this.author=author;
        this.year=year;
        this.category=category;
        this.status=status;
    }
    
    public String getTitle(){ return title; }
    public String getAuthor(){ return author; }
    public String getYear(){ return year; }
    public String getCategory(){ return category; }
    public String getStatus(){ return status; }
}
