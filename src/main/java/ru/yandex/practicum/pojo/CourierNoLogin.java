package ru.yandex.practicum.pojo;

public class CourierNoLogin {
    private final String password;

    public CourierNoLogin(String password) {
        this.password = password;
    }

    public static CourierNoLogin fromCourier(Courier courier) {
        return new CourierNoLogin(courier.getPassword());
    }
}
