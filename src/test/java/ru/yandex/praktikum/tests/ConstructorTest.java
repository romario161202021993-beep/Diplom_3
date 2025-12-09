package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    private MainPage mainPage;

    @Before
    public void setUpTest() {
        mainPage = new MainPage(driver);
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка перехода к разделу Булки в конструкторе")
    public void switchToBunsSectionTest() {
        // Сначала переходим к другому разделу
        mainPage.clickSaucesTab();

        // Затем возвращаемся к Булкам
        mainPage.clickBunsTab();

        // ОР: активен раздел Булки
        assertTrue("Раздел Булки не активен", mainPage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу Соусы в конструкторе")
    public void switchToSaucesSectionTest() {
        mainPage.clickSaucesTab();

        // ОР: активен раздел Соусы
        assertTrue("Раздел Соусы не активен", mainPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу Начинки в конструкторе")
    public void switchToFillingsSectionTest() {
        mainPage.clickFillingsTab();

        // ОР: активен раздел Начинки
        assertTrue("Раздел Начинки не активен", mainPage.isFillingsSectionActive());
    }
}
