package com.booker.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.booker.spec.Specs.request;
import static com.booker.spec.Specs.responseSpec201;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.restassured.RestAssured.given;

@DisplayName("Доступность сервиса")
public class PingApiTests {
    @Test
    @Feature("Ping")
    @Story("Ping")
    @DisplayName("Проверка доступности сервиса")
    @Severity(BLOCKER)
    void statusAvailableTest() {
        given(request)
                .when()
                .get("/ping")
                .then()
                .spec(responseSpec201);
    }


}
