package com.example;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long ID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "color")
	private String color;
	
	public Product(){}
	public Product(String name, BigDecimal price, String color) {
		this.name = name;
		this.price = price;
		this.color = color;
	}


	public long getID() {return ID;}
	public void setID(long iD) {ID = iD;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public BigDecimal getPrice() {return price;}
	public void setPrice(BigDecimal price) {this.price = price;}
	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}

	@Override
	public String toString() {
		return "Product [id=" + ID + ", name=" + name + ", price=" + price + ", color=" + color + "]";
	}
}
