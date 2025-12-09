package ru.yandex.praktikum.model;

public class UserGenerator {

    public static User getRandomUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "test_user_" + timestamp + "@yandex.ru";
        String password = "password123";
        String name = "TestUser" + timestamp;

        return new User(email, password, name);
    }

    public static User getUserWithShortPassword() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "test_user_" + timestamp + "@yandex.ru";
        String password = "12345"; // 5 символов - меньше минимума (6)
        String name = "TestUser" + timestamp;

        return new User(email, password, name);
    }
}
