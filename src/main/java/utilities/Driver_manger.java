package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver_manger {

    private static WebDriver driver;
    private static WebDriverWait wait;

	
	  public static void initialize() {
	  
	  driver = Driver_manger.getDriver(); 
	  if (driver == null)
	  { System.out.println(" WebDriver is null! Call Driver_manger.launchBrowser() first."); 
	  }
	  
	  wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
	  System.out.println("  initialized successfully"); }
	 

    public static WebDriverWait getwait() {
        return wait;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        Driver_manger.driver = driver;
    }


    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //--------->just call onley browser without download file
    public static void launchBrowser(BrowserName browser) {
        launchBrowser(browser, null); // calls main method with null
    }

    // ✅ Launch browser method using enum
    public static void launchBrowser(BrowserName browser, String downloadFolderpath) {
        try {

            switch (browser) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (downloadFolderpath != null) {
                        Map<String, Object> prefs = new HashMap<>();
                        prefs.put("download.default_directory", downloadFolderpath);
                        prefs.put("download.prompt_for_download", false);
                        prefs.put("profile.default_content_settings.popups", 0);
                        chromeOptions.setExperimentalOption("prefs", prefs);
                    }
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();
                    break;

                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    break;
            }
            // Implicit wait
         //   getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));  //all elements globally wait up to 60s
            driver.manage().window().maximize();
            initialize();
            System.out.println(" launched Browser Scessfully");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "there is a error in launched Browser");
        }
    }

    public enum BrowserName {
        CHROME,
        FIREFOX,
        EDGE
    }




}
