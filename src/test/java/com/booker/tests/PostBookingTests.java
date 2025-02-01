package com.booker.tests;

import com.booker.helpers.BookingHelper;
import com.booker.models.booking.BookingRequest;
import com.booker.models.booking.GetBookingResponse;
import com.booker.models.booking.PostBookingResponse;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.booker.spec.Specs.request;
import static com.booker.spec.Specs.responseSpec;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Booking")
@Story("Создание бронирования")
@DisplayName("Тесты API бронирования")
public class PostBookingTests {
    Faker faker = new Faker();

    BookingRequest validBooking = BookingRequest.builder()
            .firstname(faker.name().firstName())
            .lastname(faker.name().lastName())
            .totalprice(110)
            .bookingdates(BookingRequest.Bookingdates.builder()
                    .checkin("2025-01-01")
                    .checkout("2025-03-01")
                    .build())
            .depositpaid(true)
            .additionalneeds(faker.food().fruit())
            .build();

    BookingHelper bookingHelper = new BookingHelper(request, responseSpec);
    PostBookingResponse response;

    @Test
    @Severity(BLOCKER)
    @DisplayName("Создание бронирования")
    void postBookingsTest() {
        Integer bookingId = bookingHelper.createBooking(validBooking);

        assertNotNull(bookingId, "Поле bookingid должно быть не null");
        assertTrue(bookingId > 0, "Поле bookingid должно быть больше 0");

        GetBookingResponse bookingResponse = given(request)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .spec(responseSpec)
                .extract()
                .as(GetBookingResponse.class);


        assertEquals(validBooking.getFirstname(), bookingResponse.getFirstname(), "firstname не совпадает");
        assertEquals(validBooking.getLastname(), bookingResponse.getLastname(), "lastname не совпадает");
        assertEquals(validBooking.getTotalprice(), bookingResponse.getTotalprice(), "totalprice не совпадает");
        assertEquals(validBooking.getDepositpaid(), bookingResponse.getDepositpaid(), "depositpaid не совпадает");
        assertEquals(validBooking.getAdditionalneeds(), bookingResponse.getAdditionalneeds(), "additionalneeds не совпадает");
        assertEquals(validBooking.getBookingdates().getCheckin(), bookingResponse.getBookingdates().getCheckin(), "checkin не совпадает");
        assertEquals(validBooking.getBookingdates().getCheckout(), bookingResponse.getBookingdates().getCheckout(), "checkout не совпадает");
    }

    @AfterEach
    @DisplayName("Удаление бронирования")
    void deleteBooking() {
        bookingHelper.deleteBooking();
    }

}
