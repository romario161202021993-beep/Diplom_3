package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.model.User;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String USER_PATH = "/api/auth/user";

    @Step("Регистрация пользователя через API")
    public Response register(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(BASE_URL + REGISTER_PATH);
    }

    @Step("Вход пользователя через API")
    public Response login(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(BASE_URL + LOGIN_PATH);
    }

    @Step("Удаление пользователя через API")
    public void delete(String accessToken) {
        given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + USER_PATH);
    }
}
