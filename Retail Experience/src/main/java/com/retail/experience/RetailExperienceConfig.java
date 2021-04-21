package com.retail.experience;

import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.retail.experience.parser.CsvParser;
import com.retail.experience.parser.Parser;
import com.retail.experience.persistency.ComputerComponentDatabaseManager;
import com.retail.experience.persistency.InMemComputerComponentDatabaseManager;
import com.retail.experience.persistency.InMemOrderDatabaseManager;
import com.retail.experience.service.ComputerComponentService;
import com.retail.experience.service.OrderService;

@Configuration
public class RetailExperienceConfig {
	private final String PATH = "src//main//resources//Inventory.csv";
	
    @Bean
    public Parser inventoryParser() {
        return new CsvParser(PATH);
    }

    @Bean
    public ComputerComponentDatabaseManager inMemDatabase() throws SQLException {
        return new InMemComputerComponentDatabaseManager();
    }
    
    /*@Bean
    @ConditionalOnProperty(value="storage-mode", havingValue = "db",matchIfMissing = true)
    public ComputerComponentDatabaseManager h2Database (ComputerComponentRepository repo){
        return new H2ComputerComponentDatabaseManager(repo);
    }*/

    @Bean
    public ComputerComponentService computerComponentService(ComputerComponentDatabaseManager db) throws SQLException{
        return new ComputerComponentService(db);
    }
    
	@Bean
	public InMemOrderDatabaseManager orderDatabaseManager() {
		return new InMemOrderDatabaseManager();
	}
    
    @Bean
    public OrderService orderService(InMemOrderDatabaseManager db,ComputerComponentService service) {
    	return new OrderService(db,service);
    }
    
}