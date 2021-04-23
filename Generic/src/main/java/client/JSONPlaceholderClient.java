package client;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class JSONPlaceholderClient {

    public String getRESOURCE_POSTS() {
        return RESOURCE_POSTS;
    }

    public String getRESOURCE_COMMENTS() {
        return RESOURCE_COMMENTS;
    }

    public String getRESOURCE_ALBUMS() {
        return RESOURCE_ALBUMS;
    }

    public String getRESOURCE_TODOS() {
        return RESOURCE_TODOS;
    }

    public String getRESOURCE_USERS() {
        return RESOURCE_USERS;
    }

    private final String RESOURCE_POSTS = "/posts";
    private final String RESOURCE_COMMENTS = "/comments";
    private final String RESOURCE_ALBUMS = "/albums";
    private final String RESOURCE_TODOS = "/todos";
    private final String RESOURCE_USERS = "/users";


    public Response get(String url) {
        RestAssured.defaultParser = Parser.JSON;

        return given().when().get(url).then().contentType(ContentType.JSON).extract().response();
    }

    public ValidatableResponse post(HashMap jsonBody, String url) {
        RestAssured.defaultParser = Parser.JSON;

        return given().contentType(ContentType.JSON).with().body(jsonBody).when().post(url).then();
    }
}
