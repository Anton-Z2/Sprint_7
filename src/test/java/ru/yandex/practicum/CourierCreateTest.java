package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import ru.yandex.practicum.client.CourierClient;
import ru.yandex.practicum.pojo.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CourierCreateTest {

    public static final String NOT_UNIQUE_LOGIN = "Этот логин уже используется. Попробуйте другой.";
    public static final String NOT_ENOUGH_DATA_TO_CREATE = "Недостаточно данных для создания учетной записи";
    private CourierClient client = new CourierClient();
    private Courier courier = new Courier("eprasd11", "1234", "saske");
    private String courierId;

    @Test
    @DisplayName("Создание курьера")
    public void courierCreatedTest() {
        ValidatableResponse response = client.courierCreated(courier);
        response.statusCode(201).assertThat().body("ok", is(true));
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    public void twoIdenticalCouriersCreatedTest() {
        client.courierCreated(courier);
        ValidatableResponse response = client.courierCreated(courier);
        response.statusCode(409).assertThat().body("message", equalTo(NOT_UNIQUE_LOGIN));
    }

    @Test
    @DisplayName("Создание курьера без поля password")
    public void courierNoPasswordCreatedTest() {
        ValidatableResponse response = client.courierNoPasswordCreated(CourierNoPassword.fromCourier(courier));
        response.statusCode(400).assertThat().body("message", equalTo(NOT_ENOUGH_DATA_TO_CREATE));
    }

    @Test
    @DisplayName("Создание курьера без поля login")
    public void courierNoLoginCreatedTest() {
        ValidatableResponse response = client.courierNoLoginCreated(CourierNoLogin.fromCourier(courier));
        response.statusCode(400).assertThat().body("message", equalTo(NOT_ENOUGH_DATA_TO_CREATE));
    }

    @After
    @DisplayName("Удаление курьера")
    public void deleteCourier() {
        courierId = client.loginCourierAndReturnId(AccountData.fromCourier(courier));
        client.deleteCourier(courierId);
    }
}