package com.restful.Methods;

import com.restful.model.PostPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Post {
    @Test
    public void createBooking(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        PostPojo pojo = new PostPojo();
        pojo.setUsername("admin");
        pojo.setPassword("password123");
        Response response = given()
                .body(pojo)
                .header("Content-Type","application/json")
                .when()
                .post("/auth");
        response.then().statusCode(200);
        response.prettyPrint();

//  "token": "fcda61395e09c88"
        //59f561ae5e3ba20 new
    }
}
