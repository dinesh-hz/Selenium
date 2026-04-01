package page_actions;

import Page_locators.Login_Locaters;
import utilities.Bass_Class;

public class LoginPage extends Bass_Class {

	Login_Locaters Locators = new Login_Locaters();
	
	

	public void entreusername(String usernmae) {

		Locators.username.sendKeys(usernmae);
		// Locators.username.sendKeys(getCellData(2,2);

	}

	public void entrepassword(String password) {

		Locators.password.sendKeys(password);

	}

	public void clicklogin_button() {

		Locators.login_btn.click();
	}

	public void click_forgot_button() {

		Locators.Forgotpassword.click();
	}

	public String isverifyforgotpagehome() {

		return Locators.verifyforgotpage.getText();
	}

	public boolean verifyUsernameBoxVisibility() {

		boolean displayed = isDisplayed(Locators.username);

		return displayed;

	}

	public boolean verifyUsernameBoxisenabled() {

		boolean enabled = isEnabled(Locators.username);

		return enabled;

	}

	public boolean verifypasswordBoxVisibility() {

		boolean displayed = isDisplayed(Locators.password);

		return displayed;

	}

	public boolean verifypasswordBoxisenabled() {

		boolean enabled = isEnabled(Locators.password);

		return enabled;

	}

	public String verifyRequiredusernameerror() {

		return Locators.Requirederror_username_mesg.getText();

	}

	public String verifyRequiredpassworderror() {

		return Locators.Requirederror_password_mesg.getText();

	}

	public String verify_Invalid_errormessage() {

		return Locators.Invaliderror_mesg.getText();

	}

	public boolean isverifyloginbuttondisplay() {

		boolean displayed = isDisplayed(Locators.login_btn);

		return displayed;

	}

	public boolean isverifyloginbuttonenable_or_disable() {
		boolean enabled = isEnabled(Locators.login_btn);

		return enabled;

	}

}
