package com.retail.experience.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail.experience.model.ComputerComponent;
import com.retail.experience.model.Order;
import com.retail.experience.service.ComputerComponentService;
import com.retail.experience.service.OrderService;

@RestController
@RequestMapping("/api")
public class Controller {

	private final ComputerComponentService computerComponentService;
	private final OrderService orderService;
	@Autowired
	public Controller(ComputerComponentService computerComponentService, OrderService orderService) {
		this.computerComponentService = computerComponentService;
		this.orderService = orderService;
	}

	@GetMapping("/getAllComputerComponents")
	public List<ComputerComponent> getAllComputerComponents() throws SQLException {
		return computerComponentService.returnInventory();
	}
	
	@GetMapping("/getAllOrders")
	public List<Order> getAllOrders() throws SQLException{
		return orderService.returnOrder();
	}
	
	@GetMapping("/getByName/{name}")
	public ComputerComponent getComputerComponentByName(@PathVariable("name") String name) throws SQLException {
		return computerComponentService.getComputerComponentByName(name);
	}
	
	@GetMapping("/getById/{id}")
	public ComputerComponent getComputerComponentById(@PathVariable String id) throws SQLException {
		return computerComponentService.getComputerComponentById(id);
	}

	@GetMapping("/size")
	public int getNumberOfDataTableEntries() throws SQLException {
		return computerComponentService.getNumberOfComponents();
	}
	
	@PostMapping("/createOrder/{order}")
	public String createOrder(@PathVariable final String order) throws SQLException, InterruptedException{
		return orderService.processOrder(order);
	}

	@GetMapping("/getStatus/{orderID}")
	public String getOrderStatus(@PathVariable String orderID) {
		return orderService.checkStatus(orderID);
	}
	
}
