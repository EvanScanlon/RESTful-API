package com.retail.experience.persistency;

import java.sql.SQLException;
import java.util.List;

import com.retail.experience.model.ComputerComponent;

public interface ComputerComponentDatabaseManager {

	List<ComputerComponent> findAll() throws SQLException;

	ComputerComponent findById(String id);

	public void save(List<ComputerComponent> warehouse);
}
