package com.retail.experience.model;

public enum Category {
	CPU("CPU"), GPU("GPU"), Keyboard("Keyboard"), Monitor("Monitor"), Memory("Memory"), Mouse("Mouse"),
	Storage("Storage");

	public final String label;

	private Category(String label) {
		this.label = label;
	}

}