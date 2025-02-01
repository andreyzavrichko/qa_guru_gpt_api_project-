package com.booker.tests;

import com.booker.helpers.BookingHelper;
import com.booker.models.booking.BookingRequest;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.booker.spec.Specs.request;
import static com.booker.spec.Specs.responseSpec;
import static io.qameta.allure.SeverityLevel.BLOCKER;

@Feature("Booking")
@Story("Удаление бронирования")
@DisplayName("Тесты API удаления бронирования")
public class DeleteBookingTests {


    private BookingHelper bookingHelper;
    private Integer bookingId;
    private BookingRequest validBooking;

    @BeforeEach
    void setUp() {
        // Инициализируем помощника
        bookingHelper = new BookingHelper(request, responseSpec);

        // Создаем данные бронирования с использованием Faker
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

        // Создаем бронирование и сохраняем bookingId
        bookingId = bookingHelper.createBooking(validBooking);
    }

    @Test
    @Severity(BLOCKER)
    @DisplayName("Удаление бронирования")
    void deleteBookingTest() {
        bookingHelper.deleteBooking();
    }


}
