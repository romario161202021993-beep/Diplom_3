package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.UserApiClient;
import ru.yandex.praktikum.model.User;
import ru.yandex.praktikum.model.UserGenerator;
import ru.yandex.praktikum.pages.LoginPage;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    private MainPage mainPage;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private UserApiClient userApiClient;
    private String accessToken;

    @Before
    public void setUpTest() {
        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        userApiClient = new UserApiClient();

        driver.get(BASE_URL + "/register");
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации с валидными данными")
    public void successfulRegistrationTest() {
        User user = UserGenerator.getRandomUser();

        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        // ОР: после успешной регистрации открывается страница входа
        // Проверяем, что кнопка "Войти" видна
        loginPage.clickLoginButton();

        // Сохраняем токен для удаления пользователя
        accessToken = userApiClient.login(user).jsonPath().getString("accessToken");

        assertTrue("Регистрация не прошла успешно", accessToken != null);
    }

    @Test
    @DisplayName("Ошибка при регистрации с коротким паролем")
    @Description("Проверка отображения ошибки при пароле меньше 6 символов")
    public void shortPasswordRegistrationErrorTest() {
        User user = UserGenerator.getUserWithShortPassword();

        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        // ОР: отображается сообщение об ошибке "Некорректный пароль"
        assertTrue("Сообщение об ошибке не отображается",
                registerPage.isErrorMessageDisplayed());
    }

    @After
    public void cleanUp() {
        // Удаляем тестового пользователя через API
        if (accessToken != null) {
            userApiClient.delete(accessToken);
        }
    }
}
