package com.retail.experience.persistency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.retail.experience.generator.TestOrderGenerator;
import com.retail.experience.model.Order;
import com.retail.experience.persistency.InMemOrderDatabaseManager;

class InMemOrderDatabaseManagerTest {
	
	private TestOrderGenerator generator;
	private InMemOrderDatabaseManager db;
	private final String ORDERID = "orderID1";

	@BeforeEach
	public void setup() throws SQLException {
		generator = new TestOrderGenerator();
		db = new InMemOrderDatabaseManager();
	}

	@Test
	public void isStockEmptyFromEmptyDbFindAll() throws SQLException {
		List<Order> stock = db.findAll();
		assertTrue(stock.isEmpty());
	}
	
	@Test
	public void isStockNotEmptyFromDbFindAllAfterSavingDataToDb() throws IOException, SQLException {
		Order order = new Order("orderID",generator.getIDList());
		db.save(order);
		List<Order> stock = db.findAll();
		assertFalse(stock.isEmpty());
	}
	
	@Test
	public void isFindByIdCorrect() throws SQLException {
		Order order1 = new Order("orderID1",generator.getIDList());
		db.save(order1);
		Order order2 = new Order("orderID2",generator.getIDList());
		db.save(order2);
		assertEquals(order1,db.findById(ORDERID));
	}

}
