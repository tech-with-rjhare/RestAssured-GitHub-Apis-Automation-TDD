package TestGitHubApis;

import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class GetReposTests {

    @Test
    void verifyStatusCode(){
        ConfigManager.setBaseURI();
        RequestSpecification requestSpecification = given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON);
        Response response = requestSpecification.get("/users/tech-with-rjhare/repos");
        /*given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON).
                        when().get("/users/tech-with-rjhare/repos").
                        then().assertThat().statusCode(200);
        ResponseBody res = response.body();
       System.out.printf(res.prettyPrint());*/
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(dependsOnMethods = "verifyStatusCode")
    void verifyRepositoryByName(){
        ConfigManager.setBaseURI();
        String find_repo = ConfigManager.getValue("find_repository_name");
        RequestSpecification requestSpecification = given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON);
        Response response = requestSpecification.get("/users/tech-with-rjhare/repos");
        response.then().body("name",hasItem(find_repo));
    }

    @Test(dependsOnMethods = "verifyStatusCode")
    void verifyRepositoryByID(){
        ConfigManager.setBaseURI();
        int find_repo_by_ID = Integer.parseInt(ConfigManager.getValue("find_repository_by_ID"));
        RequestSpecification requestSpecification = given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON);
        Response response = requestSpecification.get("/users/tech-with-rjhare/repos");
        response.then().body("id",hasItem(find_repo_by_ID));
    }

    @Test(dependsOnMethods = "verifyRepositoryByName")
    void verifyDescription(){
        ConfigManager.setBaseURI();
        String expectedDesc = "Practicing TestNG annotations and assertions";
        RequestSpecification requestSpecification = given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON);
        Response response = requestSpecification.get("/users/tech-with-rjhare/repos");
        List<String> reposDesc = response.then().extract().body().jsonPath().getList("description");
        boolean isPresent = reposDesc.contains(expectedDesc);
        Assert.assertTrue(isPresent, "Expected repo name " + expectedDesc + " not found in response!");

    }

}
