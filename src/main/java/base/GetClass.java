package base;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetClass extends BaseClass{
    public static Response getRequest(String endpoint) {
        requestSpecification = given().header("Accept", "application/vnd.github+json").contentType(ContentType.JSON);
        response = requestSpecification.get(endpoint);
        return response;
    }

    public static Response getRequest(String endpoint, String Token) {
        requestSpecification = given()
                .auth()
                .oauth2(Token)
                .header("Accept", "application/vnd.github+json").
                contentType(ContentType.JSON);
        response = requestSpecification.get(endpoint);
        return response;
    }

    public static Response getRequest(String endpoint, Map<String, ?> pathParams) {
        requestSpecification = given().header("Accept", "application/vnd.github+json").contentType(ContentType.JSON);
        if (pathParams != null) {
            requestSpecification = requestSpecification.pathParams(pathParams);
        }
        response = requestSpecification.get(endpoint);
        return response;
    }

}
