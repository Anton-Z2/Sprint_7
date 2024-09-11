package ru.yandex.practicum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.pojo.*;

import static io.restassured.RestAssured.given;

public class CourierClient {

    private String courierId;

    @Step
    public ValidatableResponse courierCreated(Courier courier) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier")
                .then();
    }

    @Step
    public ValidatableResponse authCourier(AccountData accountData) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .body(accountData)
                .post("/api/v1/courier/login")
                .then();

    }

    @Step
    public ValidatableResponse authNoLoginCourier(AccountDataNoLogin accountDataNoLogin) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .body(accountDataNoLogin)
                .post("/api/v1/courier/login")
                .then();

    }

    @Step
    public ValidatableResponse authNoPasswordCourier(AccountDataNoPassword accountDataNoPassword) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .body(accountDataNoPassword)
                .post("/api/v1/courier/login")
                .then();
    }

    @Step
    public ValidatableResponse deleteCourier(String courierId) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .delete("/api/v1/courier/" + courierId)
                .then();
    }


    @Step
    public ValidatableResponse courierNoPasswordCreated(CourierNoPassword courierNoPassword) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .body(courierNoPassword)
                .post("/api/v1/courier")
                .then();
    }

    @Step
    public ValidatableResponse courierNoLoginCreated(CourierNoLogin courierNoLogin) {
        return given()
                .baseUri("https://qa-scooter.praktikum-services.ru")
                .header("Content-type", "application/json")
                .body(courierNoLogin)
                .post("/api/v1/courier")
                .then();
    }


    public String loginCourierAndReturnId(AccountData accountData) {
        ValidatableResponse response = authCourier(accountData);
        return courierId = response.extract().jsonPath().getString("id");
    }
}
