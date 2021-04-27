import client.NewsAPIClient;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import load.LoadBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class TestNewsApi extends NewsAPIClient {
    LoadBase loadBase;
    private String URI;
    private String endpoint;
    private String complete_URL;
    Response response;
    ValidatableResponse validatableResponse;
    private String KEY;

    @BeforeMethod
    public void setUp() {
        loadBase = new LoadBase();
        URI = loadBase.properties.getProperty("NEWSAPIURI");
        KEY= loadBase.properties.getProperty("KEY");
    }

    @Test
    public void testGetPosts() {
        endpoint = getEVERYTHING();
        complete_URL = URI + endpoint + "?q=keyword&apiKey=" + KEY;

        response = get(complete_URL);
        System.out.println("STATUS CODE: " + response.statusCode());


        response.then().assertThat().statusCode(200).body("articles.source.name", hasItem("Entrepreneur")).assertThat()
                .body("articles.source.name", hasItems("Lifehacker.com", "Entrepreneur", "Mashable", "TechCrunch"));
        response.body().prettyPeek();
    }
    @Test
    public void testPosts() {
        endpoint = getEVERYTHING();
        complete_URL = URI + endpoint + "?q=keyword&apiKey=" + KEY;

        response = get(complete_URL);
        System.out.println("STATUS CODE: " + response.statusCode());


        response.then().assertThat().statusCode(200).body("articles.source.name", hasItem("CNET")).assertThat()
                .body("articles.source.name", hasItems( "Search Engine Journal",  "Reuters",  "Ars Technica",  "Cern.ch"));
        response.body().prettyPeek();
    }
//    @Test
//    public void testPost() {
//
//        endpoint = "getPOST";
//        complete_URL = URI + endpoint;
//        response = get(complete_URL);
//
//        HashMap<String, Object> map = new HashMap<String, Object>();
//
//        map.put("name", "maxen");
//        map.put("job", "engineer");
//
//        JSONPObject myPost = new JSONPObject(map);
//        System.out.println(map);
//        System.out.println(myPost);
//
//        validatableResponse = post(map, complete_URL);
//        validatableResponse.assertThat().statusCode(HttpURLConnection.HTTP_CREATED).body("name", hasToString("maxen"));
//
//        validatableResponse.extract().response().body().prettyPeek();
//    }

        @Test
    public void testGetStatus() {
        endpoint = getEVERYTHING();
        complete_URL = URI + endpoint + "?q=keyword&apiKey=" + KEY;

        response = get(complete_URL);
        System.out.println("STATUS CODE: " + response.statusCode());

        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);

        response.body().prettyPeek();
    }
    @Test
    public void testGetTopHeadlines() {
        endpoint = getTOP_HEADLINES();
        complete_URL = URI + endpoint;

        response = get(complete_URL);

        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("name", hasSize(500));

        response.body().prettyPeek();
    }
}