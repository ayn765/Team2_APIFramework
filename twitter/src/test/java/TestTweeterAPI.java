import client.TweeterAPIClient;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import load.LoadBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class TestTweeterAPI extends TweeterAPIClient {
    LoadBase loadBase;
    Response response;
    ValidatableResponse validatableResponse;
    private String apiKey;
    private String apiKeySecret;
    private String accessToken;
    private String accessTokenSecret;

    @BeforeMethod
    public void setUp() {
        loadBase = new LoadBase();
        this.apiKey = loadBase.properties.getProperty("APIKey");
        this.apiKeySecret = loadBase.properties.getProperty("SecretAPIKey");
        this.accessToken = loadBase.properties.getProperty("AccessToken");
        this.accessTokenSecret = loadBase.properties.getProperty("SecretAccessToken");
    }

    @Test
    public void testCreateTweet() {
        validatableResponse = postTweet(apiKey, apiKeySecret, accessToken, accessTokenSecret, "Testing this tweet to delete it");

        validatableResponse.assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test
    public void testDeleteTweet() {
        String id = "1385058902014836741";
        validatableResponse = deleteTweetByID(apiKey, apiKeySecret, accessToken, accessTokenSecret, id);

        validatableResponse.assertThat().statusCode(200);
    }
}
