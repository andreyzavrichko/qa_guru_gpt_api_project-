package com.booker;

import com.booker.spec.Specs;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class BookerApiTests {
    @Test
    @Feature("Pet")
    @Story("Pet")
    @DisplayName("Find by status Available")
    @Severity(SeverityLevel.NORMAL)
    void findByStatusAvailableTest() {
        given(Specs.request)
                .when()
                .params("status", "available")
                .get("/v2/pet/findByStatus")
                .then()
                .spec(Specs.responseSpec)
                .body("id", hasSize(greaterThan(0)));
    }
}
