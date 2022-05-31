package com.restful.gtproj;

import com.restful.restfulbooker.apii.api.AuthApi;
import com.restful.restfulbooker.apii.api.BookingApi;
import com.restful.restfulbooker.apii.api.PingApi;
import com.restful.restfulbooker.apii.payload.*;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Date;

import static com.restful.restfulbooker.utils.Global.DEFAULT_PASSWORD;
import static com.restful.restfulbooker.utils.Global.DEFAULT_USER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiTest {
    @Test
    public void testHealthCheckReturns201() {
        Response response = PingApi.healthCheck();

        assertThat(response.getStatusCode() == 201).isTrue();
    }

    @Test
    public void testCreateTokenReturns200() {
        Auth auth = new Auth.Builder()
                .setUserName(DEFAULT_USER_NAME)
                .setPassword(DEFAULT_PASSWORD)
                .build();

        Response response = AuthApi.createToken(auth);

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void testGetBookingIdsReturns200() {
        Response response = BookingApi.getBookingIds();

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void testGetBookingReturns200() {
        Response response = BookingApi.getBooking(1, "application/json");

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void testGetBookingWithIncorrectAcceptReturns418() {
        Response response = BookingApi.getBooking(1, "test");

        assertThat(response.getStatusCode() == 418).isTrue();
    }

    @Test
    public void testCreateBookingReturns200() {
        BookingDates dates = new BookingDates.Builder()
                .setCheckin(new Date())
                .setCheckout(new Date())
                .build();

        Booking payload = new Booking.Builder()
                .setFirstName("Mary")
                .setLastName("Active")
                .setTotalPrice(200)
                .setDepositPaid(true)
                .setBookingDates(dates)
                .setAdditionalNeeds("None")
                .build();

        Response response = BookingApi.createBooking(payload);

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void testDeleteBookingReturns201() {
        BookingDates dates = new BookingDates.Builder()
                .setCheckin(new Date())
                .setCheckout(new Date())
                .build();

        Booking payload = new Booking.Builder()
                .setFirstName("Test")
                .setLastName("Test")
                .setTotalPrice(100)
                .setDepositPaid(true)
                .setBookingDates(dates)
                .setAdditionalNeeds("None")
                .build();

        BookingResponse bookingResponse = BookingApi.createBooking(payload).as(BookingResponse.class);

        Auth auth = new Auth.Builder()
                .setUserName(DEFAULT_USER_NAME)
                .setPassword(DEFAULT_PASSWORD)
                .build();

        AuthResponse authResponse = AuthApi.createToken(auth).as(AuthResponse.class);

        Response response = BookingApi.deleteBooking(bookingResponse.getBookingId(), authResponse.getToken());

        assertThat(response.getStatusCode() == 201).isTrue();
    }
}
