import client.NewsAPIClient;
import io.restassured.response.ValidatableResponse;
import load.LoadBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class TestNewsAPI extends NewsAPIClient {

    LoadBase loadBase;
    ValidatableResponse validatableResponse;
    private String apiKey;


    @BeforeMethod
    public void setUp() {
        loadBase = new LoadBase();
        this.apiKey = loadBase.properties.getProperty("APIKey");
    }

    @Test
    public void testGetLanguage() {
        validatableResponse = getLanguage(apiKey);

        validatableResponse.assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test
    public void testGetCountry() {
        validatableResponse = getCountry(apiKey);

        validatableResponse.assertThat().statusCode(200);
    }

    @Test
    public void testGetCategory() {
        validatableResponse = getCategory(apiKey);

        validatableResponse.assertThat().statusCode(200);
    }

    @Test
    public void testSearchForTrump() {
        validatableResponse = getSearchForTrump(apiKey);

        validatableResponse.assertThat().statusCode(200);
    }


}
