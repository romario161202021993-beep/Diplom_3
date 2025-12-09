package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.api.UserApiClient;
import ru.yandex.praktikum.model.User;
import ru.yandex.praktikum.model.UserGenerator;
import ru.yandex.praktikum.pages.ForgotPasswordPage;
import ru.yandex.praktikum.pages.LoginPage;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.RegisterPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UserApiClient userApiClient;
    private User testUser;
    private String accessToken;

    // Локатор кнопки "Оформить заказ" - появляется после успешного входа
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");

    @Before
    public void setUpTest() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        userApiClient = new UserApiClient();

        // Создаём тестового пользователя через API
        testUser = UserGenerator.getRandomUser();
        Response response = userApiClient.register(testUser);
        accessToken = response.jsonPath().getString("accessToken");
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной странице")
    @Description("Проверка успешного входа через кнопку на главной")
    public void loginViaMainPageButtonTest() {
        driver.get(BASE_URL);

        mainPage.clickLoginButton();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // ОР: после входа отображается кнопка "Оформить заказ"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isLoggedIn = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();

        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается", isLoggedIn);
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка успешного входа через кнопку Личный кабинет")
    public void loginViaPersonalAccountButtonTest() {
        driver.get(BASE_URL);

        mainPage.clickPersonalAccountButton();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // ОР: после входа отображается кнопка "Оформить заказ"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isLoggedIn = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();

        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается", isLoggedIn);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка перехода на страницу входа из формы регистрации")
    public void loginViaRegisterFormLinkTest() {
        driver.get(BASE_URL + "/register");

        registerPage.clickLoginLink();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // ОР: после входа отображается кнопка "Оформить заказ"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isLoggedIn = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();

        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается", isLoggedIn);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка перехода на страницу входа из формы восстановления пароля")
    public void loginViaForgotPasswordLinkTest() {
        driver.get(BASE_URL + "/forgot-password");

        forgotPasswordPage.clickLoginLink();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // ОР: после входа отображается кнопка "Оформить заказ"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isLoggedIn = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();

        assertTrue("Вход не выполнен - кнопка 'Оформить заказ' не отображается", isLoggedIn);
    }

    @After
    public void cleanUp() {
        // Удаляем тестового пользователя
        if (accessToken != null) {
            userApiClient.delete(accessToken);
        }
    }
}
