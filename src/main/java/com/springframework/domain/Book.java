package com.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(name="name")
    private String name;
    @Column(name="copies")
    private int copies;
    @Column(name="author")
    private String author;
    public String getName(){return this.name;}
    public int getCopies(){return this.copies;}
    public String getAuthor(){return this.author;}

    public void setName(String name){
        this.name = name;
    }
    public void setCopies(int copies){
        this.copies = copies;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public Book(){}
    public Book(String name,String author,int copies){
        this.name = name;
        this.author = author;
        this.copies = copies;
    }

}
