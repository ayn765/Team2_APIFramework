package client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ReqresClient {

    private final String EVERYTHING = "/api/users?page=2";
    private final String POST = "/api/users";
    private final String SINGLE_USER = "/api/users";

    public String getSINGLE_USER(){
        return SINGLE_USER;
    }

    public String getEVERYTHING() {
        return EVERYTHING;
    }

    public String getPOST(){
        return POST;
    }

    public Response get(String url) {
        RestAssured.defaultParser = Parser.JSON;

        return given().when().get(url).then().contentType(ContentType.JSON).extract().response();
    }

    public ValidatableResponse post(HashMap jsonBody, String url) {
        RestAssured.defaultParser = Parser.JSON;

        return given().contentType(ContentType.JSON).with().body(jsonBody).when().post(url).then();
    }

    public ValidatableResponse post1(JSONObject jObject, String url) {
        RestAssured.defaultParser = Parser.JSON;

        return given().contentType(ContentType.JSON).with().body(jObject).when().post(url).then();
    }


}
