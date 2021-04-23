import client.JSONPlaceholderClient;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import load.LoadBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;

public class TestJSONPlaceholder extends JSONPlaceholderClient{
    LoadBase loadBase;
    private String URI;
    private String endpoint;
    private String complete_URL;
    Response response;
    ValidatableResponse validatableResponse;

    @BeforeMethod
    public void setUp() {
        loadBase = new LoadBase();
        URI = loadBase.properties.getProperty("JSONPlaceholderURI");
    }

    @Test
    public void testGetPosts() {
        endpoint = getRESOURCE_POSTS();
        complete_URL = URI + endpoint;

        response = get(complete_URL);
        System.out.println("STATUS CODE: " + response.statusCode());

        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);

        response.body().prettyPeek();
    }

    @Test
    public void testGetComments() {
        endpoint = getRESOURCE_COMMENTS();
        complete_URL = URI + endpoint;

        response = get(complete_URL);

        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("id", hasSize(500));

        response.body().prettyPeek();
    }

    @Test
    public void testPostPosts() {
        endpoint = getRESOURCE_POSTS();
        complete_URL = URI + endpoint;

        HashMap<String, String> jsonBody = new HashMap<>();
        jsonBody.put("name", "Sami");
        jsonBody.put("id", "3472NY");
        jsonBody.put("address", "Long Island");

        validatableResponse = post(jsonBody, complete_URL);

        validatableResponse.assertThat().statusCode(HttpURLConnection.HTTP_UNAVAILABLE);
    }


    @Test
    public void testGetUserAddress() {
        endpoint = getRESOURCE_USERS();
        complete_URL = URI + endpoint + "?id=1";

        response = get(complete_URL);

        response.then().assertThat().statusCode(200).body("address.city", hasToString("[Gwenborough]"));

        response.body().prettyPeek();
    }

}
