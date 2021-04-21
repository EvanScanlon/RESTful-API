package com.retail.experience.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity(name = "ComputerComponent")
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@JsonDeserialize(builder = ComputerComponentBuilder.class)
public abstract class ComputerComponent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@Enumerated(EnumType.STRING)
	Category category;
	private String name;
	private String brand;
	private double price;
	private int quantity;

	public ComputerComponent(String id, Category category, String name, String brand, double price, int quantity) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
	}

	public String toString() {
		return getId() + ", " + getBrand() + " " + getName() + " " + getCategory() + ", " + getPrice();
	}

	public String getId() {
		return this.id;
	}

	public Category getCategory() {
		return this.category;
	}

	public String getName() {
		return this.name;
	}

	public String getBrand() {
		return this.brand;
	}

	public double getPrice() {
		return this.price;
	}

	public String getCategoryAndBrand() {
		return brand + " " + category.label;
	}

	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
