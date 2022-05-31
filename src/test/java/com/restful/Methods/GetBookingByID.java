package com.restful.Methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByID {
    @Test
    public void getBooking(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response;
        response = given()
                .when()
                .get("/booking/4342");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
