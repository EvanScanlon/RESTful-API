package com.retail.experience.generator;

import java.util.ArrayList;
import java.util.List;

public class TestOrderGenerator {

	public List<String> getIDList() {
		List<String> orders = new ArrayList<String>();
		orders.add("70d0c37e-634e-4a68-8862-0ba44f216f3b");
		orders.add("60d0c37e-634e-4a68-8862-0ba44f216f3b");
		orders.add("50d0c37e-634e-4a68-8862-0ba44f216f3b");
		return orders;
	}

	public String getIDListAsString() {
		return "70d0c37e-634e-4a68-8862-0ba44f216f3b,60d0c37e-634e-4a68-8862-0ba44f216f3b,50d0c37e-634e-4a68-8862-0ba44f216f3b";
	}

}
