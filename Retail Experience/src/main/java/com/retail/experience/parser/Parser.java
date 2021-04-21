package com.retail.experience.parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.retail.experience.model.ComputerComponent;

public interface Parser {

	public List<ComputerComponent> buildInventory() throws IOException, SQLException;
	
}
