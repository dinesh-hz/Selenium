package runners;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "./src/test/resources/feature_files/",


        glue = {"StepDefinition", "Helpers"},

        dryRun = false,
        monochrome = true,
        snippets = CucumberOptions.SnippetType.UNDERSCORE,
        plugin = {"pretty",

                "json:target/cucumber-reports/cucumber.json",

                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failed_scenarios.txt"}, // just chek failled sceainer faile name save to the text file
        publish = true,
        tags = ("@try")

)
public class TEST_RUNNER extends AbstractTestNGCucumberTests {
	


    // mvn test ---->only run test without Generates Reports
    // mvn clean ---->Deletes all existing files
    //mvn verify ----->Generates all Reports
    // mvn clean test ----> Cleans old data, then runs all tests fresh (no reports yet).
    //mvn clean test verify ----> Full workflow: cleans old data → runs tests → generates all reports.

}
