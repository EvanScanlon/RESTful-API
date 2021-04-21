package com.retail.experience.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.retail.experience.model.Category;
import com.retail.experience.model.ComputerComponent;
import com.retail.experience.model.ComputerComponentBuilder;

public class CsvParser implements Parser {
	private final String PATH;

	public CsvParser(String path) {
		PATH = path;
	}

	public List<ComputerComponent> buildInventory() throws IOException, SQLException {
		try (BufferedReader br = Files.newBufferedReader(Paths.get(PATH))) {
			String inventoryLine;
			List<String> inventory = new LinkedList<String>();
			List<ComputerComponent> stock = new ArrayList<ComputerComponent>();
			br.readLine(); // called once to not include the first line which is just labels
			stock.clear();
			for (int i = 0; (inventoryLine = br.readLine()) != null; i++) {
				inventoryLine = inventoryLine.replaceAll(",", "\t|\t");
				inventory = new LinkedList<String>(Arrays.asList(inventoryLine.split("\\|")));
				stock.add(buildComponent(inventory));
				inventory.clear();
			}
			return stock;
		}
	}

	public ComputerComponent buildComponent(List<String> inventory) {
		ComputerComponentBuilder builder = new ComputerComponentBuilder();
		return builder.build(inventory.get(0).trim(), asEnum(inventory.get(1).trim()), inventory.get(2).trim(),
				inventory.get(3).trim(), inventory.get(4).trim(), inventory.get(5).trim(), inventory.get(6).trim(),
				inventory.get(7).trim(), inventory.get(8).trim(), inventory.get(9).trim(), inventory.get(10).trim(),
				inventory.get(11).trim(), inventory.get(12).trim(), Double.parseDouble(inventory.get(13).trim()),
				Integer.parseInt(inventory.get(14).trim()));
	}

	public Category asEnum(String category) {
		switch (category) {
		case "CPU":
			return Category.CPU;
		case "GPU":
			return Category.GPU;
		case "Monitor":
			return Category.Monitor;
		case "Keyboard":
			return Category.Keyboard;
		case "Mouse":
			return Category.Mouse;
		case "Memory":
			return Category.Memory;
		case "Storage":
			return Category.Storage;
		}
		return null;
	}

}
