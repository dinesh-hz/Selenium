package page_actions;

import Page_locators.Singup_Locaters;
import utilities.Bass_Class;
import utilities.Config_Manager;

public class SingupPage extends Bass_Class {

	Config_Manager file = new Config_Manager();

	Singup_Locaters locters = new Singup_Locaters();

	public void filluptheNewSinguppage() throws InterruptedException {

		locters.Createaccount_btn.click();

		waitForSeconds(3000);

		locters.firstname.sendKeys(file.getProperty(("FIRST_NAME")));

		locters.surname.sendKeys(file.getProperty(("SURENAME")));

		locters.mobil_mailid.sendKeys(file.getProperty(("MAILID")));

		waitForSeconds(3000);

		try {
			locters.reentermail.sendKeys(file.getProperty(("MAILID")));

		} catch (Exception e) {
			e.getStackTrace();
		}

		locters.password.sendKeys(file.getProperty(("PASSWORD")));

		selectFromDropdown(locters.data, DropDownType.TEXT, file.getProperty(("DATA")));
		selectFromDropdown(locters.month, DropDownType.TEXT, file.getProperty(("MONTH")));
		selectFromDropdown(locters.year, DropDownType.TEXT, file.getProperty(("YEAR")));

		locters.male.click();

		waitForSeconds(3000);

		locters.signup_btn.click();

	}

}
