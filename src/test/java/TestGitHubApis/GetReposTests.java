package TestGitHubApis;

import POJO.RepoResponse;
import base.BaseTest;
import config.ConfigManager;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Log4jLogger;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class GetReposTests extends BaseTest {

    @Override
    protected void runBeforeClass() {
        requestSpecification = given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON);
        response = requestSpecification.get("/users/tech-with-rjhare/repos");
        Log4jLogger.info("Sending GET request to fetch repository details...");
        /*given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON).
                        when().get("/users/tech-with-rjhare/repos").
                        then().assertThat().statusCode(200);
        ResponseBody res = response.body();
       System.out.printf(res.prettyPrint());*/
        Log4jLogger.info("Endpoint: https://api.github.com/users/tech-with-rjhare/repo");
    }

    @Test
    void verifyStatusCode(){
        Assert.assertEquals(response.statusCode(),200);
        Log4jLogger.info("✅ Response Status: 200 OK - Repository details fetched successfully.");

    }

    @Test(dependsOnMethods = "verifyStatusCode")
    void verifyRepositoryByName(){
        String find_repo = ConfigManager.getValue("find_repository_name");
        requestSpecification = given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON);
        response = requestSpecification.get("/users/tech-with-rjhare/repos");
        Log4jLogger.info("Sending GET request to fetch repository details...");
        Log4jLogger.info("Endpoint: https://api.github.com/users/tech-with-rjhare/repo");
        Log4jLogger.info("Find Git Repo : "+find_repo);
        response.then().body("name",hasItem(find_repo));
        Log4jLogger.info("Successfully fetched repository details of repository - "+find_repo);
    }

    @Test(dependsOnMethods = "verifyStatusCode")
    void verifyRepositoryByID(){
        int find_repo_by_ID = Integer.parseInt(ConfigManager.getValue("find_repository_by_ID"));
        requestSpecification = given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON);
        response = requestSpecification.get("/users/tech-with-rjhare/repos");
        //response.then().body("id",hasItem(find_repo_by_ID));
        RepoResponse[] reposArray = response.as(RepoResponse[].class);
        LinkedList<RepoResponse> repoList = new LinkedList<>(Arrays.asList(reposArray));
        // Step 3: Extract list of IDs
        List<Integer> repoIDs = repoList.stream()
                .map(RepoResponse::getId)
                .collect(Collectors.toList());
        // Step 4: Hamcrest validation
        assertThat("Repo ID should exist", repoIDs, hasItem(find_repo_by_ID));
    }

    @Test(dependsOnMethods = "verifyRepositoryByName")
    void verifyDescription(){
        String expectedDesc = "Practicing TestNG annotations and assertions";
        requestSpecification = given().header("Accept","application/vnd.github+json").contentType(ContentType.JSON);
        response = requestSpecification.get("/users/tech-with-rjhare/repos");
        List<String> reposDesc = response.then().extract().body().jsonPath().getList("description");
        boolean isPresent = reposDesc.contains(expectedDesc);
        Assert.assertTrue(isPresent, "Expected repo name " + expectedDesc + " not found in response!");

    }

}
