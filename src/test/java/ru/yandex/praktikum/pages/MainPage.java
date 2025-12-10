package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");

    // Табы конструктора
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    // Активная секция конструктора
    private final By activeBunsSection = By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Булки']");
    private final By activeSaucesSection = By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Соусы']");
    private final By activeFillingsSection = By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажать кнопку 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Нажать кнопку 'Конструктор'")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    @Step("Нажать на таб 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Нажать на таб 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Нажать на таб 'Начинки'")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Проверить, что секция 'Булки' активна")
    public boolean isBunsSectionActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeBunsSection)).isDisplayed();
    }

    @Step("Проверить, что секция 'Соусы' активна")
    public boolean isSaucesSectionActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeSaucesSection)).isDisplayed();
    }

    @Step("Проверить, что секция 'Начинки' активна")
    public boolean isFillingsSectionActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeFillingsSection)).isDisplayed();
    }

    @Step("Проверить, что кнопка 'Оформить заказ' отображается")
    public boolean isOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
    }
}
