package ru.yandex.practicum;

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

    private CourierClient client = new CourierClient();
    private Courier courier = new Courier("eprasd2", "1234", "saske");;
    private String courierId;

    @Test
    public void courierLoginTest() {
        client.courierCreated(courier);
        ValidatableResponse response = client.authCourier(AccountData.fromCourier(courier));
        response.statusCode(200).assertThat().body("id", notNullValue());
    }

    @Test
    public void courierNoLoginTest() {
        client.courierCreated(courier);
        ValidatableResponse response = client.authNoLoginCourier(AccountDataNoLogin.fromCourier(courier));
        response.statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void courierNoPasswordTest() {
        courier = new Courier("eprasd2", "1234", "saske");
        client.courierCreated(courier);
        ValidatableResponse response = client.authNoPasswordCourier(AccountDataNoPassword.fromCourier(courier));
        response.statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void courierNoExistTest() {
        ValidatableResponse response = client.authCourier(AccountData.fromCourier(courier));
        response.statusCode(404).assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void deleteCourier(){
        courierId = client.loginCourierAndReturnId(AccountData.fromCourier(courier));
        client.deleteCourier(courierId);
    }

}
