package com.retail.experience.persistency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.retail.experience.generator.TestComputerComponentGenerator;
import com.retail.experience.model.ComputerComponent;
import com.retail.experience.persistency.ComputerComponentDatabaseManager;
import com.retail.experience.persistency.ComputerComponentRepository;
import com.retail.experience.persistency.H2ComputerComponentDatabaseManager;
@RunWith(SpringRunner.class)
@DataJpaTest
class H2ComputerComponentDatabaseManagerTest {

	@MockBean
	private ComputerComponentRepository repo;
	private TestComputerComponentGenerator generator;
	private ComputerComponentDatabaseManager db;
	private final String NAME = "Core i7-8809G";
	private final String ID = "70d0c37e-634e-4a68-8862-0ba44f216f3b";
	private List<ComputerComponent> item;
	
	@BeforeEach
	public void setup() throws SQLException {
		generator = new TestComputerComponentGenerator();
		db = new H2ComputerComponentDatabaseManager(repo);
		item = generator.getList();
	}

	@Test
	public void isStockEmptyFromEmptyDbFindAll() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		assertTrue(stock.isEmpty());
	}

	@Test
	public void isFindAllFunctional() throws IOException, SQLException {
		db.save(item);
		when(repo.findAll()).thenReturn(item);
		List<ComputerComponent> stock = db.findAll();
		assertEquals(item,stock);
	}
	
	@Test
	public void isFindByIdCorrect() throws SQLException {
		when(repo.findById(ID)).thenReturn(Optional.of(item.get(0)));
		assertEquals(NAME, db.findById(ID).getName());
	}

}
