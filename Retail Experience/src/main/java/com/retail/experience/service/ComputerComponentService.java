package com.retail.experience.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.retail.experience.model.Category;
import com.retail.experience.model.ComputerComponent;
import com.retail.experience.model.Order;
import com.retail.experience.parser.CsvParser;
import com.retail.experience.parser.Parser;
import com.retail.experience.persistency.ComputerComponentDatabaseManager;
import com.retail.experience.persistency.OrderRepository;

public class ComputerComponentService {

	private ComputerComponentDatabaseManager db;
	private Parser parser = new CsvParser("src//main//resources//Inventory.csv");

	@Autowired
	public ComputerComponentService(ComputerComponentDatabaseManager DB) throws SQLException {
		this.db = DB;
	}

	public List<ComputerComponent> returnInventory() throws SQLException {
		return db.findAll();
	}

	public void readInventory() throws SQLException, IOException {
		db.save(parser.buildInventory());
	}

	public List<ComputerComponent> searchComputerComponentByName(String name) throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		return stock.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());
	}

	public List<ComputerComponent> sortInventoryByName() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		stock.sort((ComputerComponent c1, ComputerComponent c2) -> c1.getName().compareTo(c2.getName()));
		List<ComputerComponent> sortedStock = stock;
		return sortedStock;
	}

	public List<ComputerComponent> sortInventoryByCategory() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		stock.sort((ComputerComponent c1, ComputerComponent c2) -> c1.getCategory().compareTo(c2.getCategory()));
		List<ComputerComponent> sortedStock = stock;
		return sortedStock;
	}

	public List<ComputerComponent> sortInventoryByBrand() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		stock.sort((ComputerComponent c1, ComputerComponent c2) -> c1.getBrand().compareTo(c2.getBrand()));
		List<ComputerComponent> sortedStock = stock;
		return sortedStock;
	}

	public double getAveragePriceOfInventory() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		OptionalDouble avg = stock.stream() //
				.mapToDouble(i -> i.getPrice()) //
				.average(); //

		if (avg.isPresent())
			return avg.getAsDouble();
		return 0.0;
	}

	public double getAveragePriceOfCPUs() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		OptionalDouble avg = stock.stream().filter(e -> "CPU".equals(e.getCategory().label))
				.mapToDouble(i -> i.getPrice()) //
				.average(); //
		if (avg.isPresent())
			return avg.getAsDouble();
		return 0.0;
	}

	public Optional<ComputerComponent> getCheapestItemInInventory() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		Optional<ComputerComponent> min = stock.stream() //
				.min(Comparator.comparing(i -> i.getPrice())); //
		return min;
	}

	public Optional<ComputerComponent> getMostExpensiveItemInInventory() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		Optional<ComputerComponent> max = stock.stream() //
				.max(Comparator.comparing(i -> i.getPrice())); //
		return max;
	}

	public Map<Category, ComputerComponent> getMostExpensiveItemOfEachCategory() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		Map<Category, ComputerComponent> mostExp = stock.stream()
				.collect(Collectors.toMap(ComputerComponent::getCategory, Function.identity(),
						BinaryOperator.maxBy(Comparator.comparing(ComputerComponent::getPrice))));
		return mostExp;
	}

	public Map<Category, Long> getNumberOfGroupedComponentsByCategory() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		Map<Category, Long> count = stock.stream()
				.collect(Collectors.groupingBy(ComputerComponent::getCategory, Collectors.counting()));
		return count;
	}

	public Map<String, Long> getNumberOfGroupedComponentsByCategoryAndBrand() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		Map<String, Long> count = stock.stream()
				.collect(Collectors.groupingBy(ComputerComponent::getCategoryAndBrand, Collectors.counting()));
		return count;
	}

	public ComputerComponent getComputerComponentById(String id) throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		for (ComputerComponent item : stock)
			if (item.getId().equals(id))
				return item;
		return null;
	}

	public ComputerComponent getComputerComponentByName(String name) throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		for (ComputerComponent item : stock)
			if (item.getName().equals(name))
				return item;
		return null;
	}

	public void addComputerComponent(ComputerComponent item) throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		stock.add(item);
		db.save(stock);
	}

	public int getNumberOfComponents() throws SQLException {
		List<ComputerComponent> stock = db.findAll();
		return stock.size();
	}

	public void reduceQuantities(List<String> ids) throws SQLException {
		List<ComputerComponent> warehouse = new ArrayList<ComputerComponent>();
		for (String id : ids) {
			warehouse.add(db.findById(id));

		}
		for (int i = 0; i < warehouse.size(); i++) {
			warehouse.set(i, reduceQuantity(warehouse.get(i)));
		}
		db.save(warehouse);
	}

	public ComputerComponent reduceQuantity(ComputerComponent item) {
		item.setQuantity(item.getQuantity() - 1);
		return item;
	}

}