import client.TwitterAPIClient;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.ArrayList;



public class TestTwitterAPI extends TwitterAPIClient {

    TwitterAPIClient twitterAPIClient = new TwitterAPIClient();
    ValidatableResponse response;
    ResponseSpecification checkStatusCodeAndContentType = new ResponseSpecBuilder()
            .expectStatusCode(HttpURLConnection.HTTP_OK)
            .expectContentType(ContentType.JSON).build();

    @BeforeMethod
    public void setUp() {
        twitterAPIClient.loadProp();
    }

    @Test
    public void testGetTrendsPlace() {
        String expectedResult = "Athens";
        response = twitterAPIClient.getTrendsPlace(946738);
        System.out.println(response.extract().body().asString());
        String actualResult = response.extract().body().path("[].locations[].name");
        Assert.assertEquals(actualResult, expectedResult, "Result does not match");
    }

    @Test
    public void testTwitterPost() {  //post id 1387040705529319425
        String myPost = "My twitter post.";
        response = twitterAPIClient.postATwit(myPost);
        System.out.println(response.extract().body().asString());
        String actualResult = response.extract().body().path("text").toString();
        Assert.assertEquals(actualResult, myPost, "The text does not match.");
    }

    @Test
    public void testCreateFriendship(){
        long id = 702779075094519808L;
        String expectedName = "Gawri Taniya";
        response = twitterAPIClient.createFriendship(id);
        System.out.println(response.extract().body().asString());
        String actualResult = response.extract().body().path("name");
        Assert.assertEquals(actualResult,expectedName, "The result is incorrect.");
    }


}
