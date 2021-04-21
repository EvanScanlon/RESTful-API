package com.retail.experience.parser;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.retail.experience.model.ComputerComponent;
import com.retail.experience.parser.CsvParser;
import com.retail.experience.parser.Parser;


class CsvParserTest {
	private final String PATH = "src//main//resources//Inventory.csv";
	private final String CHECK = "70d0c37e-634e-4a68-8862-0ba44f216f3b";

	@Test
	public void isCsvParserReadingCorrectly() throws IOException, SQLException {
		Parser parser = new CsvParser(PATH);
		List<ComputerComponent> stock = parser.buildInventory();
		assertTrue(stock.get(0).toString().contains(CHECK));
	}
}
