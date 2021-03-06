package com.example;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "user")   
public class User{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "ID")
    private long ID;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;


    public User() {}
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public long getID() {return this.ID; }
    public String getEmail() { return this.email;}
    public String getPassword() { return this.password;}
    
    public void setID(long id) { this.ID = id;}
    public void setEmail(String email) { this.email = email;}
    public void setPassword(String password) { this.password = password;}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", email=" + email + ", password=" + password + "]";
	}
}