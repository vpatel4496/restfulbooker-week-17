package com.restful.Methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetPing {
    @Test
    public void healthCheck() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response =  given()
                .when()
                .get("/ping");
        response.then().statusCode(201);
        response.prettyPrint();
    }
}