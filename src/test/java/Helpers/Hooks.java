package Helpers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.Driver_manger;
import utilities.Config_Manager;

public class Hooks extends Driver_manger {

	final Logger logger = LogManager.getLogger(Hooks.class);

	private static Scenario currentScenario;

	Config_Manager file_rad = new Config_Manager();

	public static String getScenarioName() {
		return currentScenario != null ? currentScenario.getName() : "UnknownScenario";
	}

	@Before
	public void setup_Browser(Scenario scenario) {

		// String filepath ="./download/";

		currentScenario = scenario;

		try {
			file_rad.file_reader();

			if (Driver_manger.getDriver() == null) {

				Driver_manger.launchBrowser(BrowserName.CHROME, null);

			} else {
				System.out.println("already the browser is opened sucessfully");
			}

		} catch (Exception e) {

			throw new RuntimeException("cucumber before method fail", e);
		}

		logger.info("Browser launched successfully.");
	}

	@After(order = 1)
	public void setup_down_Browser() {
		if (Driver_manger.getDriver() != null) {
			Driver_manger.getDriver().quit(); // Close browser
			Driver_manger.setDriver(null); // Reset for next scenario }

			logger.info(" Browser closed.");

		}
	}

	@After(order = 0)
	public void takeScreenshotOnFailure(Scenario scenario) {

	    if (scenario.isFailed()) {
	        try {
	            byte[] screenshot = ((TakesScreenshot) Driver_manger.getDriver()).getScreenshotAs(OutputType.BYTES);

	            // Add to Cucumber + Extent repotes (both use this)
	            scenario.attach(screenshot, "image/png", scenario.getName());

	            // Save locally project level
	            File folder = new File("./Screenshots/");
	            if (!folder.exists()) {
	                folder.mkdirs();
	            }

	            String scenarioName = scenario.getName().replaceAll(" ", "_");
	            
	            String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());

	            String fileName = scenarioName + "_" + timestamp + ".png";

	            FileUtils.writeByteArrayToFile( new File(folder, fileName),screenshot);
	            

	        } catch (Exception e) {
	        	
	            e.printStackTrace();
	        }
	    }
	}

}
