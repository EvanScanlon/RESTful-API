package com.retail.experience.model;

import javax.persistence.Entity;

@Entity
public class Peripheral extends ComputerComponent {
	private String dimension;
	private String color;

	public Peripheral(String id, Category category, String name, String brand, double price, int quantity,
			String dimension, String color) {
		super(id, category, name, brand, price, quantity);
		this.dimension = dimension;
		this.color = color;
	}

	public static ComputerComponent build(String id, Category category, String name, String brand, double price, int quantity, String dimension, String color) {
		return new Peripheral(id,category,name,brand,price,quantity,dimension,color);
	}
	
	public String getColor() {
		return color;
	}

	public String getDimension() {
		return dimension;
	}

}