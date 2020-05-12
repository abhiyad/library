package com.springframework.domain;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="myuser")
public class MyUser implements Serializable{
    @Id
    @Column(name="username",nullable=false)
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="name")
    private String name;
    @Column(name="role")
    private String role;
    @Column(name="issued_book")
    private String issued_book;
    public void setUsername(String username){ this.username=username; }
    public void setRole(String role){this.role=role;}
    public void setPassword(String password){
        this.password = password;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setIssued_book(String issued_book){this.issued_book = issued_book;}
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public String getName(){return this.name;}
    public String getRole(){return this.role;}
    public String getIssued_book(){return this.issued_book;}
    public MyUser(){}
    public MyUser(String name, String username,String password,String role){
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.issued_book = null;
    }
}
