package com.retail.experience.persistency;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.experience.model.ComputerComponent;

@Repository
public interface ComputerComponentRepository extends CrudRepository<ComputerComponent, String> {
	void saveAll(List<ComputerComponent> list);

	List<ComputerComponent> findAll();

	void flush();
}
