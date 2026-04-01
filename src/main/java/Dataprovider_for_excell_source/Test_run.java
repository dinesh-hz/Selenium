package Dataprovider_for_excell_source;


import org.testng.annotations.Test;

public class Test_run {

    @Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
    public void loginTest(Object username, Object password) {


        System.out.println(username + " / " + password);

        // Optional: Selenium login code
        // driver.findElement(By.id("username")).sendKeys(username);
        // driver.findElement(By.id("password")).sendKeys(password);
        // driver.findElement(By.id("login")).click();
    }
}
