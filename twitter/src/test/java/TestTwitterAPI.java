import client.TwitterAPIClient;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class TestTwitterAPI extends TwitterAPIClient {

    TwitterAPIClient twitterAPIClient = new TwitterAPIClient();
    ValidatableResponse response;

    @BeforeMethod
    public void setUp() {
        twitterAPIClient.loadProp();
    }

    @Test
    public void testGetTrendsPlace() {
        String expectedResult = "Athens";
        response = twitterAPIClient.getTrendsPlace(946738);
        System.out.println(response.extract().body().asString());
        String actualResult = response.extract().body().jsonPath().getString("[0].locations[0].name");
        System.out.println(actualResult);
        Assert.assertEquals(actualResult, expectedResult, "Result does not match");
    }

    @Test
    public void testTwitterPost() {  //post id 1387040705529319425
        String myPost = "My twitter post #2.";
        response = twitterAPIClient.postATwit(myPost);
        System.out.println(response.extract().body().asString());
        String actualResult = response.extract().body().path("text").toString();
        Assert.assertEquals(actualResult, myPost, "The text does not match.");
    }

    @Test
    public void testDeletePost(){
        String myPost = "My twitter post #2.";
        long id = 1387040705529319425L;
        response = deleteTweetByID(id);
        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(actualTweet, myPost, "The correct post was not deleted.");
    }

    @Test
    public void testCreateFriendship() {
        long id = 702779075094519808L;
        String expectedName = "Gawri Taniya";
        response = twitterAPIClient.createFriendship(id);
        System.out.println(response.extract().body().asString());
        String actualResult = response.extract().body().path("name");
        Assert.assertEquals(actualResult, expectedName, "The result is incorrect.");
    }

    @Test
    public void testGeoSearch() {
        String ip = "173.79.133.192";
        response = twitterAPIClient.getGeoSearch(ip);
        String expectedResult = "Cascades";
        String responseString = response.extract().response().body().jsonPath().getString("result.places[0].name");
        System.out.println(responseString);
        System.out.println(response.extract().body().asString());
        Assert.assertEquals(responseString, expectedResult, "The result is incorrect.");
    }

    @Test
    public void testFriendsList() {
        response = twitterAPIClient.getFriendsList();
        String[] expectedFriends = {"Gawri Taniya", "Pritam", "NASA", "Elon Musk"};
        System.out.println(response.extract().body().jsonPath().getList("users.name"));
        List<String> actual = response.extract().body().jsonPath().getList("users.name");
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (actual.get(i).equals(expectedFriends[i])) {
                flag = true;
                System.out.println("Confirmed friend: " + actual.get(i));
            } else {
                flag = false;
                System.out.println("Failed on: " + actual.get(i));
                count++;
            }
        }
        if (count > 0) {
            flag = false;
        }
        Assert.assertTrue(flag, "The friends are displayed incorrectly.");
    }

    @Test
    public void testUploadImage(){
        response = twitterAPIClient.updateImage("C:\\Users\\Erdosa\\IdeaProjects\\Team2_APIFramework\\twitter\\src\\test\\resources\\myimg.jpg");
        System.out.println(response.extract().body().asString());
    }

}
