package com.retail.experience;

import java.io.IOException;
import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("peristency")
@EntityScan("model")
public class RetailExperience {

	public static void main(String args[]) throws IOException, SQLException {
		SpringApplication.run(RetailExperience.class, args);
	}

}
