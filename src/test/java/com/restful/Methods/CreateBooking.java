package com.restful.Methods;

import com.restful.model.CreateBookingPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateBooking {

@Test
    public void createBooking(){
    RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    CreateBookingPojo createBookingPojo = new CreateBookingPojo();
    List<String> booking = new ArrayList<>();
    booking.add("2018-01-25");
    booking.add("2019-01-25");
    createBookingPojo.setFirstname("reey");
    createBookingPojo.setLastname("martin");
    createBookingPojo.setTotalprice(222);
    createBookingPojo.setDepositpaid(true);
    createBookingPojo.setBookingdates(booking);
    createBookingPojo.setAdditionalneeds("Breakfast");
    Response response = given()
            .auth().preemptive()
            .basic("admin","password123")
            //.contentType("application/json")
           // .accept("application/json")
           .header("Content-Type" ,"application/json")
           // .header("Accept" ,"application/json")
            .header("Authorization","cea28846c268f54d01fa8cbae21f3eeec1353288c0fe2c45d1ee0cbaa0d2d9d3")
            .body(createBookingPojo)
            .when()
            .post("/booking");
    response.then().statusCode(200);
    response.prettyPrint();
}
}
