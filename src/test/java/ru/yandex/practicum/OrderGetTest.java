package ru.yandex.practicum;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.yandex.practicum.client.OrderClient;
import ru.yandex.practicum.pojo.OrderGet;

import static org.hamcrest.Matchers.notNullValue;

public class OrderGetTest {

    OrderGet orderGet = new OrderGet(null, null, 3, null);
    private OrderClient client = new OrderClient();
    @Test
    public void courierLoginTest() {
        ValidatableResponse response = client.getOrder(orderGet);
        response.statusCode(200).assertThat().body("orders", notNullValue());
    }
}