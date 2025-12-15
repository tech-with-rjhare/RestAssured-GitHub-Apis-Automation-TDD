package base;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseClass {

    protected static RequestSpecification requestSpecification;
    protected static Response response;
    protected static ResponseSpecification responseSpecification;

    public static Response getRequest(String endpoint){
        requestSpecification = given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON);
        response = requestSpecification.get(endpoint);
        return response;
    }

    public static Response getRequest(String endpoint, Map<String, ?> pathParams){
        requestSpecification = given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON);
        if(pathParams != null) {
            requestSpecification = requestSpecification.pathParams(pathParams);
        }
        response = requestSpecification.get(endpoint);
        return response;
    }





}
