package ru.yandex.practicum.pojo;

public class AccountDataNoLogin {

    private final String password;

    public AccountDataNoLogin(String password) {
        this.password = password;
    }

    public static AccountDataNoLogin fromCourier(Courier courier) {
        return new AccountDataNoLogin(courier.getPassword());
    }
}
