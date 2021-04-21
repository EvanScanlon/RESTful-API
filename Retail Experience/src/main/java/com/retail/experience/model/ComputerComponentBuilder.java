package com.retail.experience.model;

public class ComputerComponentBuilder {

	public ComputerComponent build(String id, Category category, String name, String brand, String productLine,
			String numberOfCores, String processorClockSpeed, String graphicClockSpeed, String dimension,
			String resolution, String color, String interfaceType, String size, double price, int quantity) {
		switch (category) {
		case CPU:
			return ProcessingUnit.build(id, category, name, brand, price, quantity, productLine, numberOfCores,
					graphicClockSpeed, processorClockSpeed);
		case GPU:
			return ProcessingUnit.build(id, category, name, brand, price, quantity, productLine, numberOfCores,
					graphicClockSpeed, processorClockSpeed);
		case Keyboard:
			return Peripheral.build(id, category, name, brand, price, quantity, dimension, color);
		case Mouse:
			return Peripheral.build(id, category, name, brand, price, quantity, dimension, color);
		case Monitor:
			return Monitor.build(id, category, name, brand, price, quantity, dimension, color, resolution);
		case Storage:
			return MemoryUnit.build(id, category, name, brand, price, quantity, interfaceType, size);
		case Memory:
			return MemoryUnit.build(id, category, name, brand, price, quantity, interfaceType, size);
		}
		return null;
	}
}
