package ru.yandex.practicum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.pojo.Order;
import ru.yandex.practicum.pojo.OrderGet;

import static io.restassured.RestAssured.given;

public class OrderClient {

    @Step
    public ValidatableResponse orderCreate(Order order) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .body(order)
                .post("/api/v1/orders")
                .then();
    }

    @Step
    public ValidatableResponse getOrder(OrderGet orderGet) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .body(orderGet)
                .get("/api/v1/orders")
                .then();
    }

}
