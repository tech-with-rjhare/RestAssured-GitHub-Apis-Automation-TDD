package TestGitHubApis;

import POJO.CreateRepoRequest;
import base.BaseClass;
import base.PatchClass;
import config.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class PatchUpdateRepo extends BaseTest {
    public static String pathParam;

    @Override
    protected void runBeforeClass() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final String updateRepoName = ConfigManager.getValue("update_repo_name");
        final String updateRepoDesc = ConfigManager.getValue("update_repo_desc");
        final boolean isPrivate = Boolean.parseBoolean(ConfigManager.getValue("update_repo_is_private"));
        //pathParam = "/repos/test-account-rakesh/"+ConfigManager.getValue("new_repo_name");
        Map<String, Object> pathparam = Map.of(
                "owner",ConfigManager.getValue("owner_name"),
                "repo",ConfigManager.getValue("new_repo_name")
        );

        CreateRepoRequest requestBody = CreateRepoRequest.builder()
                .name(updateRepoName)
                .description(updateRepoDesc)
                .isPrivate(isPrivate)
                .build();

        response = PatchClass.patchRequest(pathparam,requestBody);
    }
    @Test
    void verifyStatusCode(){
        response.then().assertThat().statusCode(200);
    }

    @Test
    void verifyResponseHeaders(){
        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThanOrEqualTo(5000L), TimeUnit.MILLISECONDS)
                .expectStatusCode(200)
                .expectHeader("Strict-Transport-Security","max-age=31536000; includeSubdomains; preload")
                .expectHeader("Referrer-Policy","origin-when-cross-origin, strict-origin-when-cross-origin")
                .expectHeader("Content-Security-Policy", "default-src 'none'")
                .expectHeader("Server", "github.com").build();
        response.then().spec(responseSpecification);
    }
    @Test
    void verifyRepoName(){
        String actualRepoName = response.then().extract().body().jsonPath().get("name");
        String expRepoName = ConfigManager.getValue("update_repo_name");;
        Assert.assertEquals(actualRepoName,expRepoName,"Repo name not changed");
    }
    @Test
    void verifyUpdatedDescription(){
        String actualDesc = response.then().extract().body().jsonPath().get("description");
        String expDesc = ConfigManager.getValue("update_repo_desc");;
        Assert.assertEquals(actualDesc,expDesc,"Repo name not changed");
    }

}
