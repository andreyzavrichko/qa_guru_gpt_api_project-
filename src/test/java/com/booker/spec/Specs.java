package com.booker.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static com.booker.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class Specs {
    public static RequestSpecification request = with()
            .filter(withCustomTemplates())
            .baseUri("https://restful-booker.herokuapp.com")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)  // Логирование всех деталей ответа
            .expectResponseTime(Matchers.lessThan(3000L)) // Проверка времени выполнения запроса (менее 3 секунд)
            .build();

    public static ResponseSpecification responseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.ALL)
            .expectResponseTime(Matchers.lessThan(3000L))
            .build();

    public static ResponseSpecification responseSpec400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.ALL)  // Логирование всех деталей ответа
            .expectResponseTime(Matchers.lessThan(3000L)) // Проверка времени выполнения запроса (менее 3 секунд)
            .build();

    public static ResponseSpecification responseSpec404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(LogDetail.ALL)  // Логирование всех деталей ответа
            .expectResponseTime(Matchers.lessThan(3000L)) // Проверка времени выполнения запроса (менее 3 секунд)
            .build();

    public static ResponseSpecification responseSpec500 = new ResponseSpecBuilder()
            .expectStatusCode(500)
            .log(LogDetail.ALL)  // Логирование всех деталей ответа
            .expectResponseTime(Matchers.lessThan(3000L)) // Проверка времени выполнения запроса (менее 3 секунд)
            .build();
}
