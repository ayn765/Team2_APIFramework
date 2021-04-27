import client.ReqresClient;
import dataprovider.ReqresDataProvider;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import load.LoadBase;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.HttpURLConnection;
import java.util.HashMap;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;


public class TestReqresAPI extends ReqresClient{

    LoadBase loadBase;
    private String URI;
    private String endpoint;
    private String complete_URL;
    Response response;
    ValidatableResponse validatableResponse;

    @BeforeMethod
    public void setUp() {
        loadBase = new LoadBase();
        URI = loadBase.properties.getProperty("REQRESURI");
    }
    @Test
    public void testArticlesContent() {
        endpoint = getEVERYTHING();
        complete_URL = URI + endpoint;
        response = get(complete_URL);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
        response.then().body("data.email", hasItem("michael.lawson@reqres.in")).assertThat()
                .body("data.first_name", hasItems("Byron", "Michael", "Lindsay", "Tobias"));
        response.body().prettyPeek();
    }

    @Test
    public void testNumberOfDataMembers(){
        endpoint = getEVERYTHING();
        complete_URL = URI + endpoint;
        response = get(complete_URL);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
        response.then().body("total", equalTo(12)).assertThat();
        response.body().prettyPeek();
    }

    @Test
    public void testPost(){
        endpoint = "getPOST()";
        complete_URL = URI + endpoint;

        response = get(complete_URL);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Ostin");
        map.put("job", "realtor");

        JSONObject myPost = new JSONObject(map);
        System.out.println(map);
        System.out.println(myPost);

        validatableResponse = post(map, complete_URL);
        validatableResponse.assertThat().statusCode(HttpURLConnection.HTTP_CREATED).body("name", hasToString("Ostin"));

        validatableResponse.extract().response().body().prettyPeek();

    }

    @Test (dataProvider = "reqres_data", dataProviderClass = ReqresDataProvider.class)
    private void testFirstNames(int ID, String name){
        endpoint = getSINGLE_USER();
        complete_URL = URI + endpoint + "/" + ID;
        response = get(complete_URL);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_CREATED).body("first_name", hasToString(name));
    }


}
