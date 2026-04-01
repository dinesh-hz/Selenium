package page_actions;

import Page_locators.Home_Locaters;
import utilities.Bass_Class;

public class Homepage extends Bass_Class {

	Home_Locaters Locators = new Home_Locaters();

	public String verifyHomepage() {

		// String expextext = "Dashboard";
		return Locators.Dashboard.getText();

	}

}
