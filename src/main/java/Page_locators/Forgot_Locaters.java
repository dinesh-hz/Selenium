package Page_locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver_manger;

public class Forgot_Locaters {

	public Forgot_Locaters() {
		PageFactory.initElements(Driver_manger.getDriver(), this);

	}
	
	@FindBy(xpath = "//h6[text()='Reset Password']")
	public WebElement reset_password_Text;

	public WebElement getReset_password_Text() {
		return reset_password_Text;
	}
	
	

}
