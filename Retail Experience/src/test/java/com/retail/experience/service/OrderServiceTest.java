package com.retail.experience.service;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import com.retail.experience.generator.TestComputerComponentGenerator;
import com.retail.experience.generator.TestOrderGenerator;
import com.retail.experience.persistency.InMemComputerComponentDatabaseManager;
import com.retail.experience.persistency.InMemOrderDatabaseManager;
import com.retail.experience.service.ComputerComponentService;
import com.retail.experience.service.OrderService;

public class OrderServiceTest {

	private InMemOrderDatabaseManager orderDb;
	private InMemComputerComponentDatabaseManager computerComponentDb;
	private ComputerComponentService computerComponentService;
	private OrderService orderService;
	private TestOrderGenerator orderGenerator;
	private TestComputerComponentGenerator computerComponentGenerator;

	@BeforeEach
	public void setup() throws SQLException {
		orderDb = new InMemOrderDatabaseManager();
		computerComponentDb = new InMemComputerComponentDatabaseManager();
		computerComponentService = new ComputerComponentService(computerComponentDb);
		orderService = new OrderService(orderDb, computerComponentService);
		orderGenerator = new TestOrderGenerator();
		computerComponentGenerator = new TestComputerComponentGenerator();
	}

	@Test
	public void createOrderReducesQuantityTest() throws SQLException, InterruptedException {
		computerComponentDb.save(computerComponentGenerator.getList());
		int expected = computerComponentDb.findAll().get(0).getQuantity() - 1;
		orderService.buildOrder(orderGenerator.getIDListAsString());
		int actual = computerComponentDb.findAll().get(0).getQuantity();
		assertEquals(expected,actual);
	}
}
