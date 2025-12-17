package base;

import POJO.CreateRepoRequest;
import config.TokenManager;
import endpoints.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseClass {

    protected static RequestSpecification requestSpecification;
    protected static Response response;
    protected static ResponseSpecification responseSpecification;

}
