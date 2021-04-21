package com.retail.experience.service;

import com.retail.experience.model.Order;
import com.retail.experience.persistency.InMemOrderDatabaseManager;

public class OrderProcess implements Runnable {
Order order;
InMemOrderDatabaseManager db;

	public OrderProcess(Order order, InMemOrderDatabaseManager db) {
		this.order = order;
		this.db = db;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(20000); //building time
			db.save(order.updateStatus());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
