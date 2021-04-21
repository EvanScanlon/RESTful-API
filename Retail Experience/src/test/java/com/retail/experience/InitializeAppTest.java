package com.retail.experience;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.retail.experience.InitializeApp;
import com.retail.experience.generator.TestComputerComponentGenerator;
import com.retail.experience.parser.Parser;
import com.retail.experience.persistency.ComputerComponentDatabaseManager;


@SpringBootTest
public class InitializeAppTest {
	private InitializeApp initApp;
	private Parser parser;
	private ComputerComponentDatabaseManager repo;

	@BeforeEach
	public void before() throws SQLException, IOException{
		repo = mock(ComputerComponentDatabaseManager.class);
		parser = mock(Parser.class);
		initApp = new InitializeApp(parser, repo);
	}

	@Test
	void mainInitilizeTest() throws SQLException, IOException, URISyntaxException {
		TestComputerComponentGenerator generator = new TestComputerComponentGenerator();
		Mockito.when(parser.buildInventory()).thenReturn(generator.getList());
		initApp.init();
		Mockito.verify(repo).save(parser.buildInventory());
	}

	
}
