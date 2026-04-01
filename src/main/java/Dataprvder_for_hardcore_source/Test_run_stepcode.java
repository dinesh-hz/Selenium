package Dataprvder_for_hardcore_source;

import org.testng.annotations.Test;

public class Test_run_stepcode {

    @Test(dataProvider = "hai", dataProviderClass = TestData.class)
    public void loginTest(String username, String password) {


        System.out.println("Testing login with: " + username + " / " + password);
        // Add Selenium login steps here
    }
}
