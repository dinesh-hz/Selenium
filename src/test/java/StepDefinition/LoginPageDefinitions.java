package StepDefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import Helpers.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page_actions.Homepage;
import page_actions.LoginPage;
import utilities.Bass_Class;
import utilities.Config_Manager;

public class LoginPageDefinitions {

	Config_Manager file = new Config_Manager();

	protected final Logger logger = LogManager.getLogger(Hooks.class);

	LoginPage LoginPage = new LoginPage();

	Homepage Homepage = new Homepage();

	@Given("user is on the login page")
	public void user_is_on_the_login_page() {

		String URL = file.getProperty("URL");

		Bass_Class.set_URL(URL);
	//	Bass_Class.waitForPageLoad();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("done");

	}

	@Given("user entre the valid username and password")
	public void user_entre_the_valid_username_and_password(io.cucumber.datatable.DataTable dataTable) throws Exception {

		// this data taking from feature file under the stepdefinationS
		var data = dataTable.asMap(String.class, String.class);

		LoginPage.entreusername(data.get("username"));
		LoginPage.entrepassword(data.get("password"));

	}

	@Then("user click the login button")
	public void user_click_the_login_button() {
		// Validate Element is NOT Displayed
		boolean isverifyloginbuttondisplay = LoginPage.isverifyloginbuttondisplay();
		Assert.assertTrue("the login butt\t\tBass.java_wait_class(3000);\non is not  visibled",
				isverifyloginbuttondisplay);
		boolean isverifyloginbuttonenable = LoginPage.isverifyloginbuttonenable_or_disable();
		Assert.assertTrue("login button is not enabled after enter data", isverifyloginbuttonenable);
		Bass_Class.waitForSeconds(3);
		LoginPage.clicklogin_button();

	}

	@Then("user should see homepage {string}")
	public void userShouldSeeHomepage(String expecttext) {

		// step 3: use it
		Bass_Class.waitForSeconds(3);
		String verifyHomepage = Homepage.verifyHomepage();
		Assert.assertEquals("user is not Homepage now", verifyHomepage, expecttext);

	}

	@Given("user entre the invalid username and password")
	public void user_entre_the_invalid_username_and_password() throws Exception {

		LoginPage.entreusername("WorrogeA");
		LoginPage.entrepassword("dvdvddj4562");

	}

	@Then("user should see error message {string}")
	public void user_should_see_error_message(String expexterror_mesg) {

		String verify_Invalid_errormessage = LoginPage.verify_Invalid_errormessage();

		Assert.assertEquals("user could not see error mesg of username", expexterror_mesg, verify_Invalid_errormessage);

	}

	@Given("user entre the invalid username {string}")
	public void userEntreTheInvalidUsername(String string) {

		LoginPage.entreusername(string);

	}

	@When("user entre password")
	public void user_entre_password() {

		String pass = file.getProperty("PASS_WORD");

		LoginPage.entrepassword(pass);

	}

	@Then("user should see error message of username {string}")
	public void userShouldSeeErrorMessageOfUsername(String string) {

		String requiredusernameerror = LoginPage.verifyRequiredusernameerror();

		Assert.assertEquals("user cant see errormesge of username", string, requiredusernameerror);

	}

	@Given("user entre the username")
	public void user_entre_the_username() throws Exception {

		String user = file.getProperty("USER_NAME");

		LoginPage.entreusername(user);

	}

	@When("user entre invalid password")
	public void user_entre_invalid_password() {

		LoginPage.entrepassword("ggjh@55");

	}

	@Given("user dont enter anything username and password")
	public void user_dont_enter_anything_username_and_password() throws Exception {

		LoginPage.entreusername("");
		LoginPage.entrepassword("");
	}

	@Then("user should see error message of username&password needs {string}")
	public void userShouldSeeErrorMessageOfUsernamePasswordNeeds(String string) {

		String requiredusernameerror = LoginPage.verifyRequiredusernameerror();

		Assert.assertEquals("user cant see errormesge of username", string, requiredusernameerror);

		String requiredpassworderror = LoginPage.verifyRequiredpassworderror();

		Assert.assertEquals("user cant see errormesge of password", string, requiredpassworderror);

	}

	@Given("user dont entre  enything username")
	public void user_dont_entre_enything_username() {

		LoginPage.entreusername("");
	}

	@When("user dont entre  enything password")
	public void user_dont_entre_enything_password() {
		LoginPage.entrepassword("");

	}

	@Then("user should see error message of password {string}")
	public void userShouldSeeErrorMessageOfPassword(String string) {
		Bass_Class.waitForSeconds(3);
		String requiredpassworderror = LoginPage.verifyRequiredpassworderror();
		Bass_Class.waitForSeconds(3);
		Assert.assertEquals("user cant see errormesge of password", string, requiredpassworderror);

	}

	@Given("user entre on password box")
	public void user_entre_on_password_box() {

		String pass = file.getProperty("PASS_WORD");

		LoginPage.entrepassword(pass);

	}

	@Given("user click the Forgot Password link")
	public void user_click_the_forgot_password_link() {

		LoginPage.click_forgot_button();

	}

	@Then("user should go Forgot Password page {string}")
	public void user_should_go_forgot_password_page(String string) {

		String isverifyforgotpagehome = LoginPage.isverifyforgotpagehome();

		Assert.assertEquals("user dont go to forgotpasswordpage", isverifyforgotpagehome, string);

	}

	@Given("user entre the username is uses different case sensitivity")
	public void user_entre_the_username_is_uses_different_case_sensitivity() throws Exception {

		LoginPage.entreusername("admin");

	}

	@When("user entre the vaild password")
	public void user_entre_the_vaild_password() {

		String pass = file.getProperty("PASS_WORD");
		LoginPage.entrepassword(pass);
	}

	@Then("clcik login button")
	public void clcik_login_button() {

		LoginPage.clicklogin_button();

	}

	@Given("user does not entre anything")
	public void user_does_not_entre_anything() {

		LoginPage.entreusername("");
		LoginPage.entrepassword("");

	}

	@Then("user check on login button is disabled")
	public void user_check_on_login_button_is_disabled() {
		Bass_Class.waitForSeconds(3);
		boolean isverifyloginbuttonenable = LoginPage.isverifyloginbuttonenable_or_disable();

		Assert.assertFalse("the login button is not disable", isverifyloginbuttonenable);

	}

	@Given("user entre username and password vaild credentials")
	public void user_entre_username_and_password_vaild_credentials() {

		String user = file.getProperty("USER_NAME");

		String pass = file.getProperty("PASS_WORD");

		LoginPage.entreusername(user);
		Bass_Class.waitForSeconds(3);
		LoginPage.entrepassword(pass);

	}

	@Then("user should go to the homepage")
	public void user_should_go_to_the_homepage() {
		Bass_Class.waitForSeconds(3);
		String verifyHomepage = Homepage.verifyHomepage();

		Assert.assertEquals("user is redirected to homepage succesfully", verifyHomepage, "Dashboard");

	}

	@Given("user entre  username and password")
	public void user_entre_username_and_password() {

		String user = file.getProperty("USER_NAME");

		String pass = file.getProperty("PASS_WORD");

		LoginPage.entreusername(user);

		LoginPage.entrepassword(pass);
	}

	@Then("user  check on login button is enable")
	public void user_check_on_login_button_is_enable() {
		Bass_Class.waitForSeconds(3);
		boolean enable_or_disable = LoginPage.isverifyloginbuttonenable_or_disable();

		Assert.assertTrue("the login button is not enabled", enable_or_disable);

	}

	@Given("user entres an valid username and invalid password {string}")
	public void user_entres_an_valid_username_and_invalid_password(String string) {

		String user = file.getProperty("USER_NAME");

		LoginPage.entreusername(user);
		LoginPage.entrepassword(string);

	}

	@Then("user should see an error message {string}")
	public void user_should_see_an_error_message(String string) {

		String verifyerrormessage = LoginPage.verify_Invalid_errormessage();
		Bass_Class.waitForSeconds(3);

		Assert.assertEquals("user did not see any error mesg", verifyerrormessage, string);

	}
}
