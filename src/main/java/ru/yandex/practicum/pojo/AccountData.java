package ru.yandex.practicum.pojo;


public class AccountData {
    private final String login;
    private final String password;

    private AccountData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static AccountData fromCourier(Courier courier) {
        return new AccountData(courier.getLogin(), courier.getPassword());
    }


}
