package client;

import io.restassured.response.ValidatableResponse;
import load.LoadBase;


import static io.restassured.RestAssured.given;

public class TweeterAPIClient {
    LoadBase load = new LoadBase();
    String baseURI = load.properties.getProperty("TwitterURI");

    public String getRESOURCE_TWEETS() {
        return RESOURCE_TWEETS;
    }

    public String getRESOURCE_TWEETS_UPDATE() {
        return RESOURCE_TWEETS_UPDATE;
    }

    public String getRESOURCE_TWEETS_DELETE(String id) {
        return RESOURCE_TWEETS_DELETE;
    }

    private String RESOURCE_TWEETS = "/statuses";
    private String RESOURCE_TWEETS_UPDATE = "/update.json";
    private String RESOURCE_TWEETS_DELETE = "/destroy";

    public ValidatableResponse postTweet(String apiKey, String apiSecret, String token, String tokenSecret, String tweet) {

        return given().auth().oauth(apiKey, apiSecret, token, tokenSecret).param("status", tweet)
                .when().post(baseURI + RESOURCE_TWEETS + RESOURCE_TWEETS_UPDATE).then();
    }

    public ValidatableResponse deleteTweetByID(String apiKey, String apiSecret, String token, String tokenSecret, String id) {

        return given().auth().oauth(apiKey, apiSecret, token, tokenSecret).param("id", id)
                .when().post(baseURI + RESOURCE_TWEETS + RESOURCE_TWEETS_DELETE).then();
    }



}
