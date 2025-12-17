package base;

import POJO.CreateRepoRequest;
import config.TokenManager;
import endpoints.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostClass extends BaseClass{

    public static Response postRequest(CreateRepoRequest requestBody) {
        requestSpecification = given()
                .auth()
                .oauth2(TokenManager.getToken())
                .contentType(ContentType.JSON)
                .body(requestBody);
        return requestSpecification.post(Endpoints.CREATE_REPO);
    }

    public static Response postRequest(String endpoint, Headers header, CreateRepoRequest requestBody) {
        requestSpecification = given()
                .auth()
                .oauth2(TokenManager.getToken())
                .headers(header)
                .body(requestBody);
        return requestSpecification.post(endpoint);
    }

    public static Response postRequest(String endpoint, CreateRepoRequest requestBody) {
        requestSpecification = given()
                .auth()
                .oauth2(TokenManager.getToken())
                .body(requestBody);
        return requestSpecification.post(endpoint);
    }
}
