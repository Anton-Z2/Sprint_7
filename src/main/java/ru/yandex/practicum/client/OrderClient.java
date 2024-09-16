package ru.yandex.practicum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.pojo.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {

    @Step("Создание заказа")
    public ValidatableResponse orderCreate(Order order) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .body(order)
                .post("/api/v1/orders")
                .then();
    }

    @Step("Получение заказов")
    public ValidatableResponse getOrder() {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .get("/api/v1/orders")
                .then();
    }

}
