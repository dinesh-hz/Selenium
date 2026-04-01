package page_actions;

import org.testng.Assert;

import Page_locators.Forgot_Locaters;

public class Forgotpage extends Forgot_Locaters{
	
	public static Forgotpage getinstenen;

	public Forgotpage() {

	}

	public static Forgotpage getInstance() {
		if (getinstenen == null) {

			getinstenen = new Forgotpage();
		}

		return getinstenen;
	}
	
	public void verify_forgot_passwordpage(String home) {
		
		String text = getReset_password_Text().getText();

		Assert.assertEquals(text, home, "forgotpage is not visbility");
	}

}
