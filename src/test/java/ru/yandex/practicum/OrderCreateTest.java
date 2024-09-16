package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.client.OrderClient;
import ru.yandex.practicum.pojo.Order;

import static org.hamcrest.Matchers.notNullValue;


@RunWith(Parameterized.class)
public class OrderCreateTest {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    public OrderCreateTest(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters()
    public static Object[][] getParametrs() {
        return new Object[][]{
                {"Иван", "Иванов", "Адрес", "Новогиреево", "79295005001", 1, "2024-09-18", "Комментарий", new String[]{"BLACK"}},
                {"Петр", "Петров", "Адрес", "Сухаревская", "79295005002", 2, "2024-09-18", "Комментарий", new String[]{"GREY"}},
                {"Сидр", "Сидоров", "Адрес", "Комсомольская", "79295005003", 3, "2024-09-18", "Комментарий", new String[]{"GREY", "BLACK"}},
                {"Антон", "Алексеев", "Адрес", "Третьяковская", "79295005004", 4, "2024-09-18", "Комментарий", new String[]{}}
        };
    }

    @Test
    @DisplayName("Параметризованный тест создания заказов")
    public void createOrderTest() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        OrderClient orderClient = new OrderClient();
        ValidatableResponse response = orderClient.orderCreate(order);
        response.assertThat().statusCode(201).and().body("track", notNullValue());
    }

}


