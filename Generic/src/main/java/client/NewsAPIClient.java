package client;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.*;
import load.LoadBase;

public class NewsAPIClient {

    LoadBase loadBase;


    public final String RESOURCE_STATUS = "status";
    public final String RESOURCE_SOURCES = "sources";
    public final String RESOURCE_ID = "id";
    public final String RESOURCE_NAME = "name";
    public final String RESOURCE_DESCRIPTION = "&description";
    public final String RESOURCE_URL = "url";
    public final String RESOURCE_Q = "&q=trump";
    public final String RESOURCE_CATEGORY = "&category=Entertainment";
    public final String RESOURCE_LANGUAGE = "&language=en";
    public final String RESOURCE_COUNTRY = "&country=us";


    public ValidatableResponse getLanguage(String apiKey) {

        return given().auth().oauth2(apiKey).param("language").when().get(baseURI + RESOURCE_LANGUAGE).then();
    }

    public ValidatableResponse getCountry(String apiKey) {

        return given().auth().oauth2(apiKey).param("country").when().get(baseURI + RESOURCE_LANGUAGE + RESOURCE_COUNTRY).then();
    }

    public ValidatableResponse getCategory(String apiKey) {

        return given().auth().oauth2(apiKey).param("category").when().get(baseURI + RESOURCE_LANGUAGE + RESOURCE_CATEGORY).then();
    }

    public ValidatableResponse getSearchForTrump(String apiKey) {

        return given().auth().oauth2(apiKey).param("trump").when().get(baseURI + RESOURCE_LANGUAGE + RESOURCE_Q).then();

    }

}
