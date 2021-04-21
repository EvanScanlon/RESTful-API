package com.retail.experience.generator;

import java.util.ArrayList;

import com.retail.experience.model.Category;
import com.retail.experience.model.ComputerComponent;
import com.retail.experience.model.ComputerComponentBuilder;

public class TestComputerComponentGenerator {

	public ArrayList<ComputerComponent> getList() {
		ArrayList<ComputerComponent> list = new ArrayList<ComputerComponent>();
		ComputerComponentBuilder testBuilder = new ComputerComponentBuilder();
		list.add(testBuilder.build("70d0c37e-634e-4a68-8862-0ba44f216f3b", Category.CPU, "Core i7-8809G", "Intel",
				"Core i7", "4", "3.10 GHz", "1.20 GHz", "N/A", "N/A", "N/A", "N/A", "N/A", 150.0, 25));
		list.add(testBuilder.build("60d0c37e-634e-4a68-8862-0ba44f216f3b", Category.CPU, "FX-6300", "AMD", "FX-6300",
				"6", "3.10 GHz", "2.20 GHz", "N/A", "N/A", "N/A", "N/A", "N/A", 140.0, 25));
		list.add(testBuilder.build("50d0c37e-634e-4a68-8862-0ba44f216f3b", Category.GPU, "GTX 1080ti", "NVIDIA",
				"GeForce", "6", "N/A", "2.20 GHz", "N/A", "N/A", "N/A", "N/A", "N/A", 160.0, 25));
		return list;
	}

}
