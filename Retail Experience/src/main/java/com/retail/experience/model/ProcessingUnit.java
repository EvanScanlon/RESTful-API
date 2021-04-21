package com.retail.experience.model;

import javax.persistence.Entity;

@Entity
public class ProcessingUnit extends ComputerComponent {
	public String productLine;
	public String numberOfCores;
	public String graphicClockSpeed;
	public String processorClockSpeed;
	public ProcessingUnit(String id, Category category, String name, String brand, double price, int quantity,
			String productLine, String numberOfCores, String graphicClockSpeed, String processorClockSpeed) {
		super(id,category,name,brand,price,quantity);
		this.productLine = productLine;
		this.numberOfCores = numberOfCores;
		this.graphicClockSpeed = graphicClockSpeed;
		this.processorClockSpeed = processorClockSpeed;
	}
	
	public static ComputerComponent build(String id, Category category, String name, String brand, double price,
			int quantity, String productLine2, String numberOfCores2, String graphicClockSpeed,
			String processorClockSpeed) {
		return new ProcessingUnit(id, category, name, brand, price, quantity, productLine2, numberOfCores2, graphicClockSpeed, processorClockSpeed);
	}

	public String getProductLine() {
		return this.productLine;
	}

	public String getNumberOfCores() {
		return this.numberOfCores;
	}

	public String getGraphicClockSpeed() {
		return this.graphicClockSpeed;
	}
	
	public String getProcessorClockSpeed() {
		return this.processorClockSpeed;
	}
}
