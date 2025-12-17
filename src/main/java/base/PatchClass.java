package base;

import POJO.CreateRepoRequest;
import config.TokenManager;
import endpoints.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PatchClass extends BaseClass{
    public static Response patchRequest(String endpoint, String pathparam, CreateRepoRequest requestBody) {
        requestSpecification = given()
                .auth()
                .oauth2(TokenManager.getToken())
                .contentType(ContentType.JSON)
                .body(requestBody);
        return requestSpecification.patch(endpoint, pathparam);
    }

    public static Response patchRequest(Map<String, ?> pathparam, CreateRepoRequest requestBody) {
        requestSpecification = given()
                .auth()
                .oauth2(TokenManager.getToken())
                .contentType(ContentType.JSON)
                .body(requestBody);
        requestSpecification = requestSpecification.pathParams(pathparam);
        return requestSpecification.patch(Endpoints.UPDATE_REPO);
    }
}
