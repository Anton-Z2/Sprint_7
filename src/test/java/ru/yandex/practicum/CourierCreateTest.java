package ru.yandex.practicum;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.client.CourierClient;
import ru.yandex.practicum.pojo.AccountData;
import ru.yandex.practicum.pojo.Courier;
import ru.yandex.practicum.pojo.CourierNoLogin;
import ru.yandex.practicum.pojo.CourierNoPassword;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

    public class CourierCreateTest {

        private CourierClient client = new CourierClient();
        private Courier courier = new Courier("eprasd11", "1234", "saske");
        private CourierNoPassword courierNoPassword = new CourierNoPassword("1234");
        private CourierNoLogin courierNoLogin = new CourierNoLogin("eprasd11");

        private String courierId;


        @Before
        public void setUp() {
        }

        @Test
        public void courierCreatedTest() {
            ValidatableResponse response = client.courierCreated(courier);
            response.statusCode(201).assertThat().body("ok", is(true));
        }

        @Test
        public void twoIdenticalCouriersCreatedTest() {
            client.courierCreated(courier);
            ValidatableResponse response = client.courierCreated(courier);
            response.statusCode(409).assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
        }

        @Test
        public void courierNoPasswordCreatedTest() {
            ValidatableResponse response = client.courierNoPasswordCreated(courierNoPassword);
            response.statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
        }

        @Test
        public void courierNoLoginCreatedTest() {
            ValidatableResponse response = client.courierNoLoginCreated(courierNoLogin);
            response.statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
        }

        @After
        public void deleteCourier(){
            courierId = client.loginCourierAndReturnId(AccountData.fromCourier(courier));
            client.deleteCourier(courierId);
        }
    }