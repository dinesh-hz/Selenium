package Page_locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver_manger;

import static utilities.Driver_manger.getDriver;

public class Singup_Locaters {

	public Singup_Locaters() {
		PageFactory.initElements(getDriver(), this);

	}


	@FindBy(xpath = "//a[text()='Create new account']")
	public  WebElement Createaccount_btn;

	@FindBy(name = "firstname")
	public  WebElement firstname;

	@FindBy(name = "lastname")
	public  WebElement surname;

	@FindBy(name = "reg_email__")
	public  WebElement mobil_mailid;

	@FindBy(id = "password_step_input")
	public  WebElement password;

	@FindBy(id = "day") /////////////////////////////////////////
	public  WebElement data;
	@FindBy(id = "month")
	public  WebElement month;
	@FindBy(id = "year")
	public  WebElement year;
	@FindBy(xpath = "//label[text()='Female']")
	public  WebElement female;
	@FindBy(xpath = "//label[text()='Male']")
	public  WebElement male;
	@FindBy(xpath = "//label[text()='Custom']")
	public  WebElement custom;
	@FindBy(name = "reg_email_confirmation__")
	public  WebElement reentermail;
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	public  WebElement signup_btn;

	

}
