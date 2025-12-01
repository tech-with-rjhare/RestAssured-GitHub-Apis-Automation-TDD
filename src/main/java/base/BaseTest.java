package base;

import config.ConfigManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.AllureReportManager;
import utils.Log4jLogger;

import java.util.logging.LogManager;

public class BaseTest {
    protected RequestSpecification requestSpecification;
    protected Response response;
    protected ResponseSpecification responseSpecification;

    @BeforeMethod
    public void beforeMethodSetup(){
        ConfigManager.setBaseURI();
        String className = this.getClass().getSimpleName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        Log4jLogger.startTestCase(className+methodName);
    }

    @AfterMethod
    public void afterMethodTearDown(){
        Log4jLogger.endTestCase();
    }

    @AfterSuite
    public void generateAllureReport(){
        //String reportPath = System.getProperty("user.dir")+ "/Reports/";
        AllureReportManager.generateAndOpenReport(System.getProperty("user.dir")+"/target/site/allure-maven-plugin");
    }

}
