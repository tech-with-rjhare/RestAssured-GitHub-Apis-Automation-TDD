package base;

import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private static final String base_uri = "https://api.github.com";

    @BeforeClass
    public void setUp(){

    }

    @AfterClass
    public void tearDown(){
        RestAssured.reset();
    }
}
