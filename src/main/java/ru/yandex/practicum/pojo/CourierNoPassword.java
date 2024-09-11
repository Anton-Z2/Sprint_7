package ru.yandex.practicum.pojo;

public class CourierNoPassword {
    private final String login;

    public CourierNoPassword(String login) {
        this.login = login;
    }

    public static CourierNoPassword fromCourier(Courier courier) {
        return new CourierNoPassword(courier.getLogin());
    }


}
