package com.retail.experience.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String orderID;
	private List<String> computerComponentIDs;
	private Status status;

	public Order(String orderNumber, List<String> IDs) {
		this.orderID = orderNumber;
		this.computerComponentIDs = IDs;
		this.status = Status.Received;
	}
	
	public void addToOrder(String order) {
		this.computerComponentIDs.add(order);
	}
	
	@Override
	public String toString() {
		return "Order number: " + orderID + ", Computer Component IDs: " + computerComponentIDs.toString();
	}
	
	public String getOrderID() {
		return orderID;
	}

	public List<String> getComputerComponentIDs() {
		return computerComponentIDs;
	}
	
	public Order updateStatus() {
		if(this.status.equals(Status.Received))status = Status.InProgress;
		else this.status = Status.Ready;
		return this;
	}

	public Status getStatus() {
		return status;
	}
	
}