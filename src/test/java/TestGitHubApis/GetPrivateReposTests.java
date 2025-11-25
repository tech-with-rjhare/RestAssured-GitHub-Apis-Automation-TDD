package TestGitHubApis;

import config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItem;

public class GetPrivateReposTests {


    @Test()
    public void verifyStatusCode(){
        ConfigManager.setBaseURI();
        RequestSpecification  requestSpecification= given().auth().oauth2(ConfigManager.getToken());
        Response response = requestSpecification.when().get("/users/repos");
        /*ResponseBody responseBody = response.then().extract().response().getBody();
        String resBody = responseBody.asString();
        //System.out.println("Response : "+response.jsonPath());
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("Responsebody : "+responseBody.prettyPrint());*//*
        String responseInString = response.body().prettyPrint();
        //System.out.println(resp);
        JsonPath jsonPath = new JsonPath(responseInString);*/
        response.then().assertThat().statusCode(200);
    }

    @Test(dependsOnMethods = "verifyStatusCode")
    void verifyRepositoryByName(){
        ConfigManager.setBaseURI();
        RequestSpecification requestSpecification = given().auth().oauth2(ConfigManager.getToken());
        Response response = requestSpecification.get("/user/repos");
        String responseBodyInString = response.body().prettyPrint();
        //response.then().body("name",hasItem(find_repo));
        JsonPath jsonPath = new JsonPath(responseBodyInString);
        List<String> repoNames = jsonPath.getList("name");
        //System.out.println(repoNames);
        String find_repo = "Test-GitHub-APIs";
        Assert.assertTrue(repoNames.contains(find_repo));

    }
}
