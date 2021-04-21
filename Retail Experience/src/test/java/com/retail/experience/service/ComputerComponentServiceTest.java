package com.retail.experience.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.retail.experience.generator.TestComputerComponentGenerator;
import com.retail.experience.model.Category;
import com.retail.experience.model.ComputerComponent;
import com.retail.experience.persistency.InMemComputerComponentDatabaseManager;
import com.retail.experience.service.ComputerComponentService;

public class ComputerComponentServiceTest {
	private List<ComputerComponent> stockTest;
	private TestComputerComponentGenerator generator;
	private InMemComputerComponentDatabaseManager db;
	private final double AVG = 150.0;
	private ComputerComponentService service;
	private final String BRAND = "AMD";
	private final String NAME = "Core i7-8809G";
	private final String CATEGORY = "CPU";
	private final String CATEGORY_GROUP = "CPU=2";
	private final String CATEGORY_BRAND_GROUP = "GPU=1";
	private final String MOST_EXP_PER_CATEGORY = "GTX 1080ti";
	private final double AVG_CPU = 145.0;
	private final int LOWEST_PRICE = 140;

	@BeforeEach
	public void setup() throws SQLException {
		db = new InMemComputerComponentDatabaseManager();
		service = new ComputerComponentService(db);
		generator = new TestComputerComponentGenerator();
		stockTest = generator.getList();
		db.save(stockTest);
	}

	@Test
	public void testAvg() throws SQLException {
		assertEquals(AVG, service.getAveragePriceOfInventory(), 1);
	}

	@Test
	public void isSortByBrandCorrect() throws IOException, SQLException {
		stockTest = service.sortInventoryByBrand();
		assertEquals(BRAND, stockTest.get(0).getBrand().trim());
	}

	@Test
	public void isSortByNameCorrect() throws IOException, SQLException {
		stockTest = service.sortInventoryByName();
		assertEquals(NAME, stockTest.get(0).getName().trim());
	}

	@Test
	public void isSortByCategoryCorrect() throws IOException, SQLException {
		stockTest = service.sortInventoryByCategory();
		assertEquals(CATEGORY, stockTest.get(0).getCategory().label);
	}

	@Test
	public void isGetMostExpensiveItemOfEachCategoryCorrect() throws IOException, SQLException {
		Map<Category, ComputerComponent> mostExp = service.getMostExpensiveItemOfEachCategory();
		assertTrue(mostExp.toString().contains(MOST_EXP_PER_CATEGORY));
	}

	@Test
	public void isGetNumberOfGroupedComponentsByCategoryCorrect() throws IOException, SQLException {
		Map<Category, Long> count = service.getNumberOfGroupedComponentsByCategory();
		assertTrue(count.toString().trim().contains(CATEGORY_GROUP));
	}

	@Ignore
	public void isGetNumberOfGroupedComponentsByCategoryAndBrandCorrect() throws IOException, SQLException {
		Map<String, Long> count = service.getNumberOfGroupedComponentsByCategoryAndBrand();
		assertTrue(count.toString().trim().contains(CATEGORY_BRAND_GROUP));
	}

	@Test
	public void isAverageCPUCorrect() throws IOException, SQLException {
		double avg = service.getAveragePriceOfCPUs();
		assertEquals(AVG_CPU, avg, 1);
	}

	@Test
	public void isCheapestCorrect() throws IOException, SQLException {
		ComputerComponent cheap = service.getCheapestItemInInventory().get();
		assertEquals(LOWEST_PRICE, cheap.getPrice(),1);
	}

}
