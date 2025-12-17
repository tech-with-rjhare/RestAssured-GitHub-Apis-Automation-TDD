package TestGitHubApis;

import POJO.CreateRepoRequest;
import config.*;
import endpoints.Endpoints;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import static base.PostClass.postRequest;
import static org.hamcrest.Matchers.*;

public class PostCallCreateRepo extends BaseTest {

    private Response response;
    @Override
    protected void runBeforeClass(){
        final String newRepoName = ConfigManager.getValue("new_repo_name");
        final String newRepoDesc = ConfigManager.getValue("new_repo_desc");
        final String privateValue = ConfigManager.getValue("new_repo_is_private");

        final boolean isPrivate = Boolean.parseBoolean(privateValue);

        CreateRepoRequest requestBody = CreateRepoRequest.builder()
                .name(newRepoName)
                .description(newRepoDesc)
                .isPrivate(isPrivate)
                .hasWiki(true)
                .hasProjects(true)
                .hasIssues(true)
                .autoInit(true)
                .licenseTemplate("mit")
                .gitignoreTemplate("Java")
                .build();

        //response = BaseClass.postRequest(requestBody);
        response = postRequest(Endpoints.CREATE_REPO,requestBody);
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
