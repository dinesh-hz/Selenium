package Page_locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver_manger;

public class Home_Locaters {
	
	
	
	
	public Home_Locaters() {
        PageFactory.initElements(Driver_manger.getDriver(), this);

	}

	@FindBy(xpath = "//h6[text()='Dashboard']")
	public WebElement Dashboard;

	

}
