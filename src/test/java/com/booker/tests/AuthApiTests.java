package com.booker.tests;

import com.booker.models.auth.AuthRequest;
import com.booker.models.auth.AuthResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.booker.spec.Specs.request;
import static com.booker.spec.Specs.responseSpec;
import static io.qameta.allure.SeverityLevel.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Feature("Login")
@Story("Auth")
@DisplayName("Авторизация")
public class AuthApiTests {
    AuthRequest validAuth = AuthRequest.builder()
            .username("admin")
            .password("password123")
            .build();

    AuthRequest authWithoutUsername = AuthRequest.builder()
            .password("password123")
            .build();

    AuthRequest authWithoutPassword = AuthRequest.builder()
            .username("admin")
            .build();

    AuthRequest authNotFound = AuthRequest.builder()
            .username("user")
            .password("12345")
            .build();

    @Test
    @DisplayName("Проверка успешного получения токена")
    @Severity(BLOCKER)
    void authSuccessTest() {
        AuthResponse response = given(request)
                .body(validAuth)
                .when()
                .post("/auth")
                .then()
                .spec(responseSpec)
                .extract()
                .as(AuthResponse.class);

        assertThat(response.getToken()).isNotEmpty();
    }

    @Test
    @DisplayName("Проверка получения токена без username")
    @Severity(NORMAL)
    void authWithoutUsernameTest() {
        given(request)
                .body(authWithoutUsername)
                .when()
                .post("/auth")
                .then()
                .spec(responseSpec)
                .body("reason", equalTo("Bad credentials"));

    }

    @Test
    @DisplayName("Проверка получения токена без password")
    @Severity(NORMAL)
    void authWithoutPasswordTest() {
        given(request)
                .body(authWithoutPassword)
                .when()
                .post("/auth")
                .then()
                .spec(responseSpec)
                .body("reason", equalTo("Bad credentials"));
    }

    @Test
    @DisplayName("Проверка получения токена несуществующим пользователем")
    @Severity(MINOR)
    void authUserNotFoundTest() {
        given(request)
                .body(authNotFound)
                .when()
                .post("/auth")
                .then()
                .spec(responseSpec)
                .body("reason", equalTo("Bad credentials"));
    }


}
