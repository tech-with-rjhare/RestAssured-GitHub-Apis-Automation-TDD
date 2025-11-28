package TestGitHubApis;

import base.BaseClass;
import config.*;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRepoCall extends BaseClass {

    @BeforeClass
    void setUp() throws InterruptedException {
        Thread.sleep(5000);
        ConfigManager.setBaseURI();
        requestSpecification = given().auth().oauth2(TokenManager.getToken());
        String pathParam = "/repos/"+ConfigManager.getValue("owner_name")+"/"+ConfigManager.getValue("update_repo_name");
        response = requestSpecification.delete(pathParam);
        System.out.println();
    }

    @Test
    void verifyStatusCode(){
        response.then().assertThat().statusCode(204);
    }

    @Test(dependsOnMethods = "verifyStatusCode")
    void verifyHeader(){
        Map<String, Object> headers = new LinkedHashMap<>(Map.of("github-authentication-token-expiration", "2026-02-24 08:09:30 UTC"
                                                                ,"Referrer-Policy","origin-when-cross-origin, strict-origin-when-cross-origin"
                                                                ,"Content-Security-Policy","default-src 'none'"
                                                                ,"Server","github.com"));

        responseSpecification = new ResponseSpecBuilder().expectHeaders(headers).build();
        response.then().spec(responseSpecification);
    }

    @Test(dependsOnMethods = "verifyHeader")
    void verifyResponseBodyIsEmpty(){
        response.then().body(equalTo(""));
    }

    @Test(dependsOnMethods = "verifyResponseBodyIsEmpty")
    void verifyRepoIsDeleted() throws InterruptedException{
        Thread.sleep(3000);
        String pathParam = "/repos/"+ConfigManager.getValue("owner_name")+"/"+ConfigManager.getValue("update_repo_name");
        response = requestSpecification.delete(pathParam);
        response.then().assertThat().statusCode(404);
    }

}
