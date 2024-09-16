package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.yandex.practicum.client.OrderClient;

import static org.hamcrest.Matchers.notNullValue;

public class OrderGetTest {

    private OrderClient client = new OrderClient();
    @Test
    @DisplayName("Получение заказов без передаваемых параметров")
    public void getOrdersTest() {
        ValidatableResponse response = client.getOrder();
        response.statusCode(200).assertThat().body("orders", notNullValue());
    }
}