package com.retail.experience;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.retail.experience.parser.Parser;
import com.retail.experience.persistency.ComputerComponentDatabaseManager;

@Component
public class InitializeApp {
	private final Parser inventoryParser;
	private final ComputerComponentDatabaseManager inventory;

	@Autowired
	public InitializeApp(Parser inventoryParser, ComputerComponentDatabaseManager inventoryRepo) throws SQLException, IOException {
		this.inventoryParser = inventoryParser;
		this.inventory = inventoryRepo;
	}

	@PostConstruct
	public void init() throws IOException, URISyntaxException, SQLException {
		inventory.save(inventoryParser.buildInventory());
	}

}