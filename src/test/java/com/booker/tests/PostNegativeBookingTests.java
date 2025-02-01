package com.booker.tests;

import com.booker.models.booking.BookingRequest;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.booker.spec.Specs.request;
import static com.booker.spec.Specs.responseSpec500;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.restassured.RestAssured.given;

@Feature("Booking")
@Story("Создание бронирования - валидации")
@DisplayName("Тесты API бронирования")
public class PostNegativeBookingTests {
    Faker faker = new Faker();
    BookingRequest bookingWithoutFirstname = BookingRequest.builder()
            .lastname(faker.name().lastName())
            .totalprice(110)
            .bookingdates(BookingRequest.Bookingdates.builder()
                    .checkin("2025-01-01")
                    .checkout("2025-03-01")
                    .build())
            .depositpaid(true)
            .additionalneeds(faker.food().fruit())
            .build();

    BookingRequest bookingWithoutLastname = BookingRequest.builder()
            .firstname(faker.name().firstName())
            .totalprice(110)
            .bookingdates(BookingRequest.Bookingdates.builder()
                    .checkin("2025-01-01")
                    .checkout("2025-03-01")
                    .build())
            .depositpaid(true)
            .additionalneeds(faker.food().fruit())
            .build();

    BookingRequest bookingWithoutTotalPrice = BookingRequest.builder()
            .firstname(faker.name().firstName())
            .totalprice(110)
            .bookingdates(BookingRequest.Bookingdates.builder()
                    .checkin("2025-01-01")
                    .checkout("2025-03-01")
                    .build())
            .depositpaid(true)
            .additionalneeds(faker.food().fruit())
            .build();

    @Test
    @Severity(BLOCKER)
    @DisplayName("Создание бронирования - без полезной нагрузки")
    void postBookingsWithoutBodyTest() {
        given(request)
                .when()
                .post("/booking")
                .then()
                .spec(responseSpec500);
    }

    @Test
    @Severity(BLOCKER)
    @DisplayName("Создание бронирования - без firstname")
    void postBookingsWithoutFirstnameTest() {
        given(request)
                .body(bookingWithoutFirstname)
                .when()
                .post("/booking")
                .then()
                .spec(responseSpec500);
    }

    @Test
    @Severity(BLOCKER)
    @DisplayName("Создание бронирования - без lastname")
    void postBookingsWithoutLastnameTest() {
        given(request)
                .body(bookingWithoutLastname)
                .when()
                .post("/booking")
                .then()
                .spec(responseSpec500);
    }

    @Test
    @Severity(BLOCKER)
    @DisplayName("Создание бронирования - без totalPrice")
    void postBookingsWithoutTotalPriceTest() {
        given(request)
                .body(bookingWithoutTotalPrice)
                .when()
                .post("/booking")
                .then()
                .spec(responseSpec500);
    }


}
