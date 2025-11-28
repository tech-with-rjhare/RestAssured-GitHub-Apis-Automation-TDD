package TestGitHubApis;

import base.BaseClass;
import config.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostCallCreateRepo extends BaseClass {

    @BeforeClass
    void SetUp(){
        ConfigManager.setBaseURI();
        final String newRepoName = ConfigManager.getValue("new_repo_name");
        final String newRepoDesc = ConfigManager.getValue("new_repo_desc");
        final String isPrivate = ConfigManager.getValue("new_repo_is_private");
        String requestBody = "{\n" +
                "    \"name\": \"" + newRepoName + "\",\n" +
                "    \"description\": \"" + newRepoDesc + "\",\n" +
                "    \"homepage\": \"https://github.com\",\n" +
                "    \"private\": " + isPrivate + ",\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_projects\": true,\n" +
                "    \"has_wiki\": true\n" +
                "}";
        requestSpecification = given().auth().oauth2(TokenManager.getToken()).contentType(ContentType.JSON).body(requestBody);
        //requestSpecification.log().all();
        response = requestSpecification.post("/user/repos");
    }

    @Test
    void verifyCreateNewRepo(){
        response.then().statusCode(201);
    }

    @Test
    void verifyResponseHeaders(){
        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThanOrEqualTo(5000L), TimeUnit.MILLISECONDS)
                .expectStatusCode(201)
                .expectHeader("Strict-Transport-Security","max-age=31536000; includeSubdomains; preload")
                .expectHeader("Referrer-Policy","origin-when-cross-origin, strict-origin-when-cross-origin")
                .expectHeader("Content-Security-Policy", "default-src 'none'")
                .expectHeader("Server", "github.com").build();
        response.then().spec(responseSpecification);


    }

    @Test
    void verifyVisibility(){
        String body = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(body);
        Assert.assertEquals(jsonPath.get("visibility"),ConfigManager.getValue("new_repo_visibility"));
    }



}
