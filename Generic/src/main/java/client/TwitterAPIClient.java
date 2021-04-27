package client;

import io.restassured.response.ValidatableResponse;

import java.io.FileInputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TwitterAPIClient {

    private String APIKey;
    private String APISecretKey;
    private String Token;
    private String TokenSecret;
    Properties prop;
    private final String BASE_URL = "https://api.twitter.com/1.1";

    private final String GET_TRENDS_ENDPOINT = "/trends/place.json";
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String RESOURCE_TWEETS_DELETE = "/statuses/destroy.json";
    private final String CREATE_FRIENDSHIP = "/friendships/create.json";
    private final String GEO_SEARCH = "/geo/search.json";
    private final String FRIENDS_LIST = "/friends/list.json";
    private final String UPDATE_IMAGE = "/account/update_profile_image.json";


    private String URI;

    public void loadProp() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream((System.getProperty("user.dir") + "/src/main/resources/secret.properties"));
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.APIKey = prop.getProperty("APIKey");
        this.APISecretKey = prop.getProperty("APISecretKey");
        this.Token = prop.getProperty("AccessToken");
        this.TokenSecret = prop.getProperty("AccessTokenSecret");
    }


    public ValidatableResponse getTrendsPlace(int id) {
        URI = BASE_URL + GET_TRENDS_ENDPOINT;
        return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret)
                .queryParam("id", id)
                .when().get(URI).then();
    }

    public ValidatableResponse postATwit(String twitterPost){
            return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret)
                    .param("status", twitterPost)
                    .when().post(this.BASE_URL + this.CREATE_TWEET_ENDPOINT)
                    .then();
        }

    public ValidatableResponse deleteTweetByID(long id) {

        return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret).queryParam("id", id)
                .when().post(this.BASE_URL + RESOURCE_TWEETS_DELETE).then();
    }


    public ValidatableResponse createFriendship(long id){
        return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret).param("id", id)
                .when().post(this.BASE_URL + CREATE_FRIENDSHIP). then();
    }
    public ValidatableResponse getGeoSearch(String ip){
        return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret)
                .param("ip", ip)
                .when().get(this.BASE_URL + this.GEO_SEARCH)
                .then();
    }
    public ValidatableResponse getFriendsList(){
        return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret)
                .when().get(this.BASE_URL + this.FRIENDS_LIST)
                .then();
    }

    public ValidatableResponse updateImage(String path){
        return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret).param("image", path)
                .when().post(this.BASE_URL + UPDATE_IMAGE). then();
    }

}
