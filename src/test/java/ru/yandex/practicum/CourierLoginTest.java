package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import ru.yandex.practicum.client.CourierClient;
import ru.yandex.practicum.pojo.AccountData;
import ru.yandex.practicum.pojo.AccountDataNoLogin;
import ru.yandex.practicum.pojo.AccountDataNoPassword;
import ru.yandex.practicum.pojo.Courier;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginTest {

    public static final String NOT_ENOUGH_DATA = "Недостаточно данных для входа";
    public static final String NO_ACCOUNT_FIND = "Учетная запись не найдена";
    private CourierClient client = new CourierClient();
    private Courier courier = new Courier("eprasd2", "1234", "saske");
    private Courier courierWrongLog = new Courier("rer3dfrw23", "1234", "saske");

    private Courier courierWrongPass = new Courier("eprasd2", "2", "saske");
    private String courierId;

    @Test
    @DisplayName("Авторизация курьера")
    public void courierLoginTest() {
        client.courierCreated(courier);
        ValidatableResponse response = client.authCourier(AccountData.fromCourier(courier));
        response.statusCode(200).assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация курьера без поля login")
    public void courierNoLoginTest() {
        client.courierCreated(courier);
        ValidatableResponse response = client.authNoLoginCourier(AccountDataNoLogin.fromCourier(courier));
        response.statusCode(400).assertThat().body("message", equalTo(NOT_ENOUGH_DATA));
    }

    @Test
    @DisplayName("Авторизация курьера без поля password")
    public void courierNoPasswordTest() {
        client.courierCreated(courier);
        ValidatableResponse response = client.authNoPasswordCourier(AccountDataNoPassword.fromCourier(courier));
        response.statusCode(400).assertThat().body("message", equalTo(NOT_ENOUGH_DATA));
    }

    @Test
    @DisplayName("Авторизация курьера c некорректным логином")
    public void courierWrongLogLoginTest() {
        client.courierCreated(courier);
        ValidatableResponse response = client.authCourier(AccountData.fromCourier(courierWrongLog));
        response.statusCode(404).assertThat().body("message", equalTo(NO_ACCOUNT_FIND));
    }

    @Test
    @DisplayName("Авторизация курьера c некорректным паролем")
    public void courierWrongPassLoginTest() {
        client.courierCreated(courier);
        ValidatableResponse response = client.authCourier(AccountData.fromCourier(courierWrongPass));
        response.statusCode(404).assertThat().body("message", equalTo(NO_ACCOUNT_FIND));
    }

    @Test
    @DisplayName("Авторизация несуществующего курьера")
    public void courierNoExistTest() {
        ValidatableResponse response = client.authCourier(AccountData.fromCourier(courier));
        response.statusCode(404).assertThat().body("message", equalTo(NO_ACCOUNT_FIND));
    }

    @After
    @DisplayName("Удаление курьера")
    public void deleteCourier() {
        courierId = client.loginCourierAndReturnId(AccountData.fromCourier(courier));
        client.deleteCourier(courierId);
    }

}
