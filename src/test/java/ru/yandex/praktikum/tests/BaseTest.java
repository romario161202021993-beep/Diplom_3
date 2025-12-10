package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @Before
    public void setUp() {
        // Читаем, какой браузер нужно запустить: chrome или yandex
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        if ("chrome".equals(browser)) {
            // Обычный Google Chrome
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

        } else if ("yandex".equals(browser)) {
            // Яндекс.Браузер через ChromeDriver

            // 1. Путь к browser.exe Яндекс.Браузера — ПРОВЕРЬ у себя!
            // Открой свойства ярлыка Яндекс.Браузера и скопируй путь к файлу browser.exe
            String yandexPath = "C:\\Users\\Kharchenko Family\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.setBinary(new File(yandexPath)); // говорим драйверу использовать бинарник Яндекс.Браузера
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);

        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser + ". Используй chrome или yandex.");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
