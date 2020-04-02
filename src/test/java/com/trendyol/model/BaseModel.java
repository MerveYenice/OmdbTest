package com.trendyol.model;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseModel {

    final String BASE_URL="http://www.omdbapi.com/";
    final String API_KEY ="6dc74324";

    public RequestSpecification SetRequest(){
        RestAssured.baseURI = BASE_URL;

        RequestSpecification request = RestAssured
                .given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()));
        return request;
    }

    public Response DoRequest(){
        RequestSpecification request =  SetRequest();
        Response response = request
                .when()
                .get("?apikey=".concat(API_KEY).concat("&s=harry+potter"))
                .then()
                .contentType(ContentType.JSON).extract().response();
        return response;
    }
}
