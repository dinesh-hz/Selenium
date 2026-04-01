package StepDefinition;

import io.cucumber.java.en.And;
import page_actions.LoginPage;
import page_actions.SingupPage;

import java.io.IOException;

public class SingupPageDefinitions {
	SingupPage singup = new SingupPage();
	LoginPage login = new LoginPage();

	// public static final Logger LOGER=LogManager.getLogger(SingupPageDefinitions.class);

	  @And("user click the  createnewaccount butto")
	  public void userClickTheCreatenewaccountButto() throws InterruptedException, IOException {

		 // login.navigatetowebapge();
		  singup.filluptheNewSinguppage();

		  // LOGER.info("hai");
	  }
	

}
