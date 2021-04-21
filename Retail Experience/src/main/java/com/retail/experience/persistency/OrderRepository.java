package com.retail.experience.persistency;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.experience.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
	void saveAll(List<Order> list);

	List<Order> findAll();

	void flush();
}
