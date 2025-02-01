package com.booker.tests;

import com.booker.models.booking.GetBookingResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.booker.spec.Specs.*;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.MINOR;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Booking")
@Story("Получение бронирований")
@DisplayName("Тесты API бронирования")
public class GetBookingTests {

    @Test
    @Severity(BLOCKER)
    @DisplayName("Получение бронирований по диапазону дат (должны быть результаты)")
    void getBookingsByDateRangeTest() {
        List<Map<String, Object>> response = given(request)
                .param("checkin", "2024-01-01")
                .param("checkout", "2025-01-31")
                .when()
                .get("/booking")
                .then()
                .spec(responseSpec)
                .extract()
                .as(new TypeRef<>() {
                });

        assertThat(response).isNotEmpty();
        assertThat(response).allSatisfy(booking -> {
            assertThat(booking).containsKey("bookingid");
            assertThat(booking.get("bookingid")).isInstanceOf(Number.class);
        });
    }

    @Test
    @Severity(MINOR)
    @DisplayName("Получение бронирований по диапазону дат (ожидается пустой список)")
    void getBookingsByEmptyDateRangeTest() {
        List<Object> response = given(request)
                .param("checkin", "2000-01-01")
                .param("checkout", "2000-01-31")
                .when()
                .get("/booking")
                .then()
                .spec(responseSpec)
                .extract()
                .as(new TypeRef<>() {
                });

        assertThat(response).isEmpty();
    }


    @Test
    @Severity(BLOCKER)
    @DisplayName("Получение информации о конкретном бронировании")
    void getBookingByIdTest() {
        GetBookingResponse response = given(request)
                .when()
                .get("/booking/1")
                .then()
                .spec(responseSpec)
                .extract()
                .as(GetBookingResponse.class);

        assertThat(response.getFirstname()).isNotEmpty();
        assertThat(response.getLastname()).isNotEmpty();
    }

    @Test
    @Severity(MINOR)
    @DisplayName("Попытка получения несуществующего бронирования (должен быть 404)")
    void getNonExistentBookingTest() {
        given(request)
                .when()
                .get("/booking/999999999999999")
                .then()
                .spec(responseSpec404);

    }

}
