package client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;


import static io.restassured.RestAssured.given;

public class NewsAPIClient {

    private final String EVERYTHING= "/v2/everything";
    public final String TOP_HEADLINES = "/v2/top-headlines";
    public final String SOURCES = "/v2/sources";

    public String getEVERYTHING() {
        return EVERYTHING;
    }

    public String getTOP_HEADLINES() {
        return TOP_HEADLINES;
    }

    public String getSOURCES() {
        return SOURCES;
    }

    public Response get(String url) {
        RestAssured.defaultParser = Parser.JSON;

        return given().when().get(url).then().contentType(ContentType.JSON).extract().response();
    }

    public ValidatableResponse post(HashMap jsonBody, String url) {
        RestAssured.defaultParser = Parser.JSON;

        return given().contentType(ContentType.JSON).with().body(jsonBody).when().post(url).then();
    }


}
