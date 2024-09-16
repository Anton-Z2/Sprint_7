package ru.yandex.practicum.pojo;

public class AccountDataNoPassword {
    private final String login;

    private AccountDataNoPassword(String login) {
        this.login = login;
    }

    public static AccountDataNoPassword fromCourier(Courier courier) {
        return new AccountDataNoPassword(courier.getLogin());
    }
}
