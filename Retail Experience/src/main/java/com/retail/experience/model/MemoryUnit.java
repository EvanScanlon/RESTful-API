package com.retail.experience.model;

import javax.persistence.Entity;

@Entity
public class MemoryUnit extends ComputerComponent {
	public String size;
	public String interfaceType;

	public String getSize() {
		return size;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public MemoryUnit(String id, Category category, String name, String brand, double price, int quantity,
			String interfaceType, String size) {
		super(id, category, name, brand, price, quantity);
		this.size = size;
		this.interfaceType = interfaceType;
	}

	public static ComputerComponent build(String id, Category category, String name, String brand, double price, int quantity,
			String interfaceType, String size) {
		return new MemoryUnit(id,category,name,brand,price,quantity,interfaceType,size);
	}

}

