package com.retail.experience.persistency;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.retail.experience.model.ComputerComponent;

public class InMemComputerComponentDatabaseManager implements ComputerComponentDatabaseManager {

	Map<String, ComputerComponent> inventory = new HashMap<String, ComputerComponent>();
	
	
	@Override
	public List<ComputerComponent> findAll() throws SQLException {
		return inventory.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
	}
	
	@Override
	public ComputerComponent findById(String id) {
		return inventory.get(id);
	}
	
	@Override
	public void save(List<ComputerComponent> input) {
		for(ComputerComponent item : input) {
			inventory.put(item.getId(),item);
		}
	}

}