package com.booker.tests;

import com.booker.helpers.BookingHelper;
import com.booker.models.booking.BookingRequest;
import com.booker.models.booking.GetBookingResponse;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.booker.spec.Specs.request;
import static com.booker.spec.Specs.responseSpec;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Booking")
@Story("Обновление бронирования")
@DisplayName("Тесты API обновление бронирования")
public class PutBookingTests {

    private BookingHelper bookingHelper;
    private Integer bookingId;
    private BookingRequest validBooking;

    @BeforeEach
    @DisplayName("Создание бронирования")
    void setUp() {
        bookingHelper = new BookingHelper(request, responseSpec);

        Faker faker = new Faker();
        validBooking = BookingRequest.builder()
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

        bookingId = bookingHelper.createBooking(validBooking);
    }

    @Test
    @Severity(BLOCKER)
    @DisplayName("Обновление бронирования")
    void updateBookingTest() {
        Faker faker = new Faker();
        BookingRequest updatedBooking = BookingRequest.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(150)
                .bookingdates(BookingRequest.Bookingdates.builder()
                        .checkin("2025-02-01")
                        .checkout("2025-04-01")
                        .build())
                .depositpaid(false)
                .additionalneeds(faker.food().ingredient())
                .build();

        GetBookingResponse updateResponse = bookingHelper.updateBooking(updatedBooking);

        assertEquals(updatedBooking.getFirstname(), updateResponse.getFirstname(), "firstname не совпадает");
        assertEquals(updatedBooking.getLastname(), updateResponse.getLastname(), "lastname не совпадает");
        assertEquals(updatedBooking.getTotalprice(), updateResponse.getTotalprice(), "totalprice не совпадает");
        assertEquals(updatedBooking.getDepositpaid(), updateResponse.getDepositpaid(), "depositpaid не совпадает");
        assertEquals(updatedBooking.getAdditionalneeds(), updateResponse.getAdditionalneeds(), "additionalneeds не совпадает");
        assertEquals(updatedBooking.getBookingdates().getCheckin(), updateResponse.getBookingdates().getCheckin(), "checkin не совпадает");
        assertEquals(updatedBooking.getBookingdates().getCheckout(), updateResponse.getBookingdates().getCheckout(), "checkout не совпадает");
    }

    @AfterEach
    @DisplayName("Удаление бронирования")
    void deleteBooking() {
        bookingHelper.deleteBooking();
    }
}
