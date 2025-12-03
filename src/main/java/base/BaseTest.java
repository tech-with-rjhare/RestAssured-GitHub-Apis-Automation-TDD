package base;

import config.ConfigManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.*;
import utils.Log4jLogger;

public abstract class BaseTest {
    protected RequestSpecification requestSpecification;
    protected Response response;
    protected ResponseSpecification responseSpecification;
    protected abstract void runBeforeClass();

    @BeforeTest
    public void setUp(){
        ConfigManager.setBaseURI();
    }

    @BeforeClass
    public void beforeClassSetup(){
        runBeforeClass();
    }

    @BeforeMethod
    public void beforeMethodSetUp(){
        String className = this.getClass().getSimpleName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        Log4jLogger.startTestCase(className+methodName);
    }


    @AfterMethod
    public void afterMethodTearDown(){
        Log4jLogger.endTestCase();
    }



}
