package com.booker.helpers;

import com.booker.models.booking.BookingRequest;
import com.booker.models.booking.GetBookingResponse;
import com.booker.models.booking.PostBookingResponse;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Getter;

import static com.booker.spec.Specs.responseSpec201;
import static io.restassured.RestAssured.given;


public class BookingHelper {

    private final RequestSpecification requestSpec;
    private final ResponseSpecification responseSpec;
    @Getter
    private Integer bookingId;
    private final String token; // сохраненный токен авторизации

    public BookingHelper(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        this.requestSpec = requestSpec;
        this.responseSpec = responseSpec;
        this.token = getAuthToken();
    }

    /**
     * Получает токен авторизации для операций с бронированием.
     *
     * @return токен авторизации
     */
    private String getAuthToken() {
        // Тело запроса для получения токена
        String authBody = "{\"username\" : \"admin\", \"password\" : \"password123\"}";

        // Выполняем запрос и извлекаем токен
        String authToken = given(requestSpec)
                .contentType(ContentType.JSON)
                .body(authBody)
                .when()
                .post("/auth")
                .then()
                .spec(responseSpec)
                .extract()
                .path("token");

        if (authToken == null || authToken.isEmpty()) {
            throw new IllegalStateException("Не удалось получить токен авторизации");
        }

        return authToken;
    }

    /**
     * Создает бронирование и сохраняет bookingId.
     *
     * @param bookingRequest данные бронирования
     * @return bookingId созданного бронирования
     */
    public Integer createBooking(BookingRequest bookingRequest) {
        PostBookingResponse response = given(requestSpec)
                .contentType(ContentType.JSON)
                .body(bookingRequest)
                .when()
                .post("/booking")
                .then()
                .spec(responseSpec)
                .extract()
                .as(PostBookingResponse.class);

        if (response.getBookingid() == null || response.getBookingid() <= 0) {
            throw new IllegalStateException("Неверный bookingid, бронирование не создано корректно");
        }
        this.bookingId = response.getBookingid();

        return bookingId;
    }

    /**
     * Обновляет бронирование по сохранённому bookingId.
     *
     * @param updatedBooking данные для обновления бронирования
     * @return обновленный ответ бронирования
     */
    public GetBookingResponse updateBooking(BookingRequest updatedBooking) {
        // Передаем токен в Cookie или заголовке (зависит от реализации API)


        return given(requestSpec)
                .contentType(ContentType.JSON)
                // Передаем токен в Cookie или заголовке (зависит от реализации API)
                .cookie("token", token)
                .body(updatedBooking)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .spec(responseSpec)
                .extract()
                .as(GetBookingResponse.class);
    }

    /**
     * Удаляет бронирование по сохранённому bookingId.
     */
    public void deleteBooking() {
        if (bookingId == null) {
            throw new IllegalStateException("BookingId не установлен. Невозможно удалить бронирование.");
        }
        given(requestSpec)
                .cookie("token", token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .spec(responseSpec201);


    }
}

