package TestGitHubApis;

import base.BaseClass;
import config.ConfigManager;
import org.testng.annotations.*;
import utils.Log4jLogger;

public abstract class BaseTest extends BaseClass{

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

    protected abstract void runBeforeClass();
}
