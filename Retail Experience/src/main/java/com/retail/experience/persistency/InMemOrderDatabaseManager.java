package com.retail.experience.persistency;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.retail.experience.model.ComputerComponent;
import com.retail.experience.model.Order;

public class InMemOrderDatabaseManager {

	Map<String, Order> orders = new HashMap<String, Order>();

	public List<Order> findAll() throws SQLException {
		return orders.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
	}

	public Order findById(String id) {
		return orders.get(id);
	}

	public void saveAll(List<Order> stock) throws SQLException {
		orders = stock.stream().collect(Collectors.toMap(Order::getOrderID, Function.identity()));
	}

	public void save(Order order) {
		orders.put(order.getOrderID(), order);
	}

}