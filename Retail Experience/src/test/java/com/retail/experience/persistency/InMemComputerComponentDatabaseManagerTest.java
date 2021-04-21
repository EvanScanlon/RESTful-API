package com.retail.experience.persistency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.retail.experience.generator.TestComputerComponentGenerator;
import com.retail.experience.model.ComputerComponent;
import com.retail.experience.persistency.ComputerComponentDatabaseManager;
import com.retail.experience.persistency.InMemComputerComponentDatabaseManager;

class InMemComputerComponentDatabaseManagerTest {
	
	private TestComputerComponentGenerator generator;
	private ComputerComponentDatabaseManager db;
	private final String NAME = "GTX 1080ti";
	private final String ID = "50d0c37e-634e-4a68-8862-0ba44f216f3b";

	@BeforeEach
	public void setup() throws SQLException {
		generator = new TestComputerComponentGenerator();
		db = new InMemComputerComponentDatabaseManager();
	}

	@Test
	public void isStockEmptyFromEmptyDbFindAll() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		assertTrue(stock.isEmpty());
	}
	
	@Test
	public void isStockNotEmptyFromDbFindAllAfterSavingDataToDb() throws IOException, SQLException {
		db.save(generator.getList());
		List<ComputerComponent> stock = db.findAll();
		assertFalse(stock.isEmpty());
	}
	
	@Test
	public void isFindByIdCorrect() throws SQLException {
		db.save(generator.getList());
		assertEquals(NAME,db.findById(ID).getName());
	}

}
