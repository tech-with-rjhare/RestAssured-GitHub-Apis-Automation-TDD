package base;

import config.TokenManager;
import endpoints.Endpoints;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeleteClass extends BaseClass{


    public static Response deleteRequest(Map<String, Object> pathparam){
        requestSpecification = given()
                .auth()
                .oauth2(TokenManager.getToken());
        requestSpecification = requestSpecification.pathParams(pathparam);
        return requestSpecification.delete(Endpoints.DELETE_REPO);
    }

    public static Response deleteRequest(String endpoint, Map<String, Object> pathparam){
        requestSpecification = given()
                .auth()
                .oauth2(TokenManager.getToken());
        requestSpecification = requestSpecification.pathParams(pathparam);
        return requestSpecification.delete(endpoint);
    }

    public static Response deleteRequest(String endpoint, Map<String, Object> pathparam,String Token){
        requestSpecification = given()
                .auth()
                .oauth2(Token);
        requestSpecification = requestSpecification.pathParams(pathparam);
        return requestSpecification.delete(endpoint);
    }

}
