package com.retail.experience.model;

import javax.persistence.Entity;

@Entity
public class Monitor extends Peripheral {
	String resolution;

	public String getResolution() {
		return resolution;
	}

	public Monitor(String id, Category category, String name, String brand, double price, int quantity,
			String dimension, String color, String resolution) {
		super(id, category, name, brand, price, quantity, dimension, color);
		this.resolution = resolution;
	}

	public static ComputerComponent build(String id, Category category, String name, String brand, double price, int quantity,
			String dimension, String color, String resolution) {
		return new Monitor(id,category,name,brand,price,quantity,dimension,color,resolution);
	}

}
