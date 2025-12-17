package TestGitHubApis;

import config.*;
import endpoints.Endpoints;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.LinkedHashMap;
import java.util.Map;

import static base.DeleteClass.deleteRequest;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRepoCall extends BaseTest {

    private Response response;
    @Override
    protected void runBeforeClass() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> pathparam = Map.of(
                "repo",ConfigManager.getValue("update_repo_name"),
                "owner",ConfigManager.getValue("owner_name")
        );
        response = deleteRequest(pathparam);
    }

    @Test
    void verifyStatusCode(){
        response.then().assertThat().statusCode(204);
    }

    @Test(dependsOnMethods = "verifyStatusCode")
    void verifyHeader(){
        Map<String, Object> headers = new LinkedHashMap<>(Map.of("github-authentication-token-expiration", "2026-02-24 08:09:30 UTC"
                                                                ,"Referrer-Policy","origin-when-cross-origin, strict-origin-when-cross-origin"
                                                                ,"Content-Security-Policy","default-src 'none'"
                                                                ,"Server","github.com"));

        responseSpecification = new ResponseSpecBuilder().expectHeaders(headers).build();
        response.then().spec(responseSpecification);
    }

    @Test(dependsOnMethods = "verifyHeader")
    void verifyResponseBodyIsEmpty(){
        response.then().body(equalTo(""));
    }

    @Test(dependsOnMethods = "verifyResponseBodyIsEmpty")
    void verifyRepoIsDeleted() throws InterruptedException{
        Thread.sleep(3000);
        Map<String, Object> pathparam = Map.of(
                "repo",ConfigManager.getValue("update_repo_name")
        );
        response = deleteRequest(Endpoints.DELETE_REPO_BY_NAME,pathparam,TokenManager.getToken());
        response.then().assertThat().statusCode(404);
    }

}
