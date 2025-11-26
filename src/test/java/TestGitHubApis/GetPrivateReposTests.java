package TestGitHubApis;

import config.ConfigManager;
import config.TokenManager;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
    import static org.hamcrest.Matchers.*;

public class GetPrivateReposTests {

    @Test()
    public void verifyStatusCode(){
        ConfigManager.setBaseURI();
        RequestSpecification  requestSpecification= given().auth().oauth2(TokenManager.getToken());
        Response response = requestSpecification.when().get("/user/repos");
        /*ResponseBody responseBody = response.then().extract().response().getBody();
        String resBody = responseBody.asString();
        //System.out.println("Response : "+response.jsonPath());
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("Responsebody : "+responseBody.prettyPrint());
        String responseInString = response.body().prettyPrint();
        //System.out.println(resp);
        JsonPath jsonPath = new JsonPath(responseInString);*/
        response.then().assertThat().statusCode(200);
    }

    @Test(priority = 2, dependsOnMethods = "verifyStatusCode")
    void verifyResponseHeader(){
        ConfigManager.setBaseURI();
        RequestSpecification  requestSpecification= given().auth().oauth2(TokenManager.getToken());
        Response response = requestSpecification.when().get("/user/repos");

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThanOrEqualTo(2000L), TimeUnit.MILLISECONDS)
                .expectHeader("Cache-Control","private, max-age=60, s-maxage=60")
                .expectHeader("github-authentication-token-expiration", "2026-02-24 08:09:30 UTC")
                .expectHeader("X-GitHub-Media-Type","github.v3; format=json")
                .expectHeader("x-github-api-version-selected","2022-11-28")
                .expectHeader("Referrer-Policy","origin-when-cross-origin, strict-origin-when-cross-origin")
                .expectHeader("Content-Security-Policy","default-src 'none'")
                .expectHeader("Content-Encoding","gzip")
                .expectHeader("Transfer-Encoding","chunked")
                .expectHeader("Server","github.com").build();

        response.then().spec(responseSpecification);

    }

    @Test(priority = 3, dependsOnMethods = "verifyStatusCode")
    void verifyRepositoryByName(){
        ConfigManager.setBaseURI();
        RequestSpecification requestSpecification = given().auth().oauth2(TokenManager.getToken());
        Response response = requestSpecification.get("/user/repos");
        //String responseBodyInString = response.body().prettyPrint();
        String responseBodyInString = response.body().asString();
        //response.then().body("name",hasItem(find_repo));
        JsonPath jsonPath = new JsonPath(responseBodyInString);
        List<String> repoNames = jsonPath.getList("name");
        //System.out.println(repoNames);
        String find_repo = "Test-GitHub-APIs";
        Assert.assertTrue(repoNames.contains(find_repo));

    }

    @Test(priority = 1, dependsOnMethods = "verifyStatusCode")
    void verifyResponseContentType(){
        ConfigManager.getBaseURI();
        Response response = given().auth().oauth2(TokenManager.getToken()).get("/user/repos");
        response.then().assertThat().contentType(ContentType.JSON);

        //String contentType = response.getContentType();
        //response.then().assertThat().contentType(containsString("application/json"));
        //response.then().assertThat().contentType(containsString(contentType));
        //response.then().header("Content-Type",containsString("application/json"));
        //Assert.assertEquals(contentType,"application/json; charset=utf-8");

    }
}
