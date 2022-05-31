package com.restful.Methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetBooking {
    Response response;
  @Test
    public void inIt(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = given()
                .when()
                .get("/booking");
        response.then().statusCode(200);
      response.prettyPrint();

    }

}
//923