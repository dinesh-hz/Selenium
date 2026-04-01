package Dataprvder_for_hardcore_source;
import org.testng.annotations.DataProvider;

public class TestData {


    // this hardcore dataprovider



        // this are hardcode all datas here ...u can see the values...that is out put also it can  come out two value like username and password



    @DataProvider(name = "hai")
    public Object[][] getData() {
        return new Object[][]{
                {"admin@example.comm", "admin123"},
                {"user1@example.comm", "user123"},
                {"user2@example.comm", "user456"}
        };
    }


}
