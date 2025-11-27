package TestGitHubApis;

import config.ConfigManager;
import config.TokenManager;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RedirectConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class PatchUpdateRepo {
    public RequestSpecification requestSpecification;
    public Response response;
    public static String pathParam;

    @BeforeClass
    void setUp(){
        ConfigManager.setBaseURI();

       // Enable redirect handling for PATCH
        RestAssured.config = RestAssured.config()
                .redirect(RedirectConfig.redirectConfig().followRedirects(true));

        final String updateRepoName = ConfigManager.getValue("update_repo_name");
        final String updateRepoDesc = ConfigManager.getValue("update_repo_desc");
        final String isPrivate = ConfigManager.getValue("update_repo_is_private");
        pathParam = "/repos/test-account-rakesh/"+ConfigManager.getValue("new_repo_name");

        String requestBody = "{\n" +
                "    \"name\": \"" + updateRepoName + "\",\n" +
                "    \"description\": \"" + updateRepoDesc + "\"\n" +
                "}";
        requestSpecification = given().auth().oauth2(TokenManager.getToken()).contentType(ContentType.JSON).body(requestBody);
        response = requestSpecification.patch(pathParam);
        System.out.println(pathParam);
    }
    @Test
    void verifyStatusCode(){
        response.then().assertThat().statusCode(200);
    }

    @Test
    void verifyResponseHeaders(){
        ResponseSpecification requestSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThanOrEqualTo(5000L), TimeUnit.MILLISECONDS)
                .expectStatusCode(200)
                .expectHeader("Strict-Transport-Security","max-age=31536000; includeSubdomains; preload")
                .expectHeader("Referrer-Policy","origin-when-cross-origin, strict-origin-when-cross-origin")
                .expectHeader("Content-Security-Policy", "default-src 'none'")
                .expectHeader("Server", "github.com").build();
        response.then().spec(requestSpec);
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
