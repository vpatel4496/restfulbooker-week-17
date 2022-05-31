package com.restful.Methods;

import com.restful.model.UpdateBookingPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UpdateBooking {
@Test
    public void updateBooking(){
    RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    UpdateBookingPojo updateBookingPojo = new UpdateBookingPojo();
    updateBookingPojo.setFirstname("james");
    updateBookingPojo.setLastname("bwn");
    updateBookingPojo.setTotalprice(333);
    updateBookingPojo.setDepositpaid(true);
    updateBookingPojo.setAdditionalneeds("Breakfast");
    List<String> booking = new ArrayList<>();
    booking.add("2018-01-25");
    booking.add("2019-01-25");
    updateBookingPojo.setBookingdates(booking);


    Response response = given()
            .body(updateBookingPojo)
            .contentType("application/json")
            .accept("application/json")
            //.cookie("59f561ae5e3ba20")
            .cookie("token","59f561ae5e3ba20")
            .when()
            .put("booking/4342");
    response.then().statusCode(200);
    response.prettyPrint();
}
}
