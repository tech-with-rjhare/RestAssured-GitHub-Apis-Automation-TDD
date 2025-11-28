package base;

import config.ConfigManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseClass {
    protected RequestSpecification requestSpecification;
    protected Response response;
    protected ResponseSpecification responseSpecification;
    @BeforeMethod
    void BasicSetup(){

    }

}
