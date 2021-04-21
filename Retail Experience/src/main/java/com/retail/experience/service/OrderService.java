package com.retail.experience.service;

import com.retail.experience.model.Order;
import com.retail.experience.persistency.InMemOrderDatabaseManager;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class OrderService {

	private final InMemOrderDatabaseManager db;
	private final ComputerComponentService computerComponentService;
	private UUID uniqueKey;
	
	@Autowired
	public OrderService(InMemOrderDatabaseManager db, ComputerComponentService computerComponentService) {
		this.db = db;
		this.computerComponentService = computerComponentService;
	}

	public void save(Order order) throws SQLException {
		db.save(order);
	}
	
	public List<Order> returnOrder() throws SQLException{
		return db.findAll();
	}
	
	public String processOrder(String request) throws SQLException, InterruptedException {
		Order order = buildOrder(request);
		save(order);
		save(order.updateStatus());
		new Thread(new OrderProcess(order,this.db)).start();
		return generateOrderMessage(order);
	}
	
	public Order buildOrder(String request) throws SQLException, InterruptedException {
		List<String> ids = Arrays.asList(request.split("\\s*,\\s*"));
		computerComponentService.reduceQuantities(ids);// reduces the quantities of the associated items by one
		uniqueKey = UUID.randomUUID();
		return new Order(uniqueKey.toString(), ids);
	}
	
	public String generateOrderMessage(Order order) {
		if(order==null)return "Unable to process order";
		return "Order with ID: " + order.getOrderID() + " is " + order.getStatus() + ".";
	}

	public String checkStatus(String orderID) {
		return generateOrderMessage(db.findById(orderID));
	}
	
}