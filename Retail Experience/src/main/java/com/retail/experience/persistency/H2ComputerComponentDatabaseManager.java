package com.retail.experience.persistency;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.retail.experience.model.ComputerComponent;

public class H2ComputerComponentDatabaseManager implements ComputerComponentDatabaseManager {

	private final ComputerComponentRepository repo;
	
	@Autowired
	public H2ComputerComponentDatabaseManager(ComputerComponentRepository repo) {
		this.repo = repo;
	}

	@Override
	public void save(List<ComputerComponent> list) {
		repo.saveAll(list);
		repo.flush();
	}
	
	@Override
	public ComputerComponent findById(String id){
		return repo.findById(id).get();
	}

	@Override
	public List<ComputerComponent> findAll() throws SQLException {
		return repo.findAll();
	}


}
