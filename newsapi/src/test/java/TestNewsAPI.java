import client.NewsAPIClient;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import load.LoadBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;


import static org.hamcrest.Matchers.*;

public class TestNewsAPI extends NewsAPIClient {
    LoadBase loadBase;
    private String URI;
    public String KEY;
    private String endpoint;
    private String complete_URL;
    Response response;
    ValidatableResponse validatableResponse;

    @BeforeMethod
    public void setUp() {
        loadBase = new LoadBase();
        URI = loadBase.properties.getProperty("NEWSAPIURI");
        KEY = loadBase.properties.getProperty("KEY");
    }

    @Test
    public void testStatus() {
        endpoint = getEVERYTHING();
        complete_URL = URI + endpoint + "?q=keyword&apiKey=" + KEY;

        response = get(complete_URL);
        System.out.println("STATUS CODE: " + response.statusCode());

        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);

        response.body().prettyPeek();
    }

    @Test
    public void testNumberOfArticles() {
        endpoint = getEVERYTHING();
        complete_URL = URI + endpoint + "?q=keyword&apiKey=" + KEY;

        response = get(complete_URL);

        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("totalResults", equalTo(1998));

        response.body().prettyPeek();
    }


    @Test
    public void testArticlesContent() {
        endpoint = getEVERYTHING();
        complete_URL = URI + endpoint + "?q=keyword&apiKey=" + KEY;

        response = get(complete_URL);

        response.then().assertThat().statusCode(200).body("articles.source.name", hasItem("Entrepreneur")).assertThat()
                .body("articles.source.name", hasItems("Lifehacker.com", "Entrepreneur", "Mashable", "TechCrunch"));

        response.body().prettyPeek();
    }

    @Test
    public void testArticlesContentTitle() {
        endpoint = getEVERYTHING();
        complete_URL = URI + endpoint + "?q=keyword&apiKey=" + KEY;
        response = get(complete_URL);
        response.then().assertThat().statusCode(200).body("articles.author", hasItem("Brendan Hesse"));
        response.body().prettyPeek();
    }


}

