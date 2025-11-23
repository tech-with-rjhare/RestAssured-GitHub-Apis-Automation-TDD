package TestGitHubApis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetReposTests {

    @Test
    void verifyStatusCode(){
        RestAssured.baseURI = "https://api.github.com";
        given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON).
                when().get("/users/tech-with-rjhare/repos").
                then().assertThat().statusCode(200);
    }

}
