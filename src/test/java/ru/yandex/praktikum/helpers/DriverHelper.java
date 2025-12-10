package ru.yandex.praktikum.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;

public class DriverHelper {

    private static final String BROWSER_PROPERTY_FILE = "src/test/resources/browser.properties";

    public static WebDriver initDriver() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(BROWSER_PROPERTY_FILE)) {
            properties.load(input);
        }

        String browserProperty = properties.getProperty("testBrowser");
        System.out.println("browserProperty = " + browserProperty);

        if (browserProperty == null || browserProperty.isEmpty()) {
            throw new RemoteException("Browser undefined");
        }

        String browserName = browserProperty.toUpperCase();
        System.out.println("Browser name from properties: " + browserName);

        BrowserType browserType = BrowserType.valueOf(browserName);

        switch (browserType) {
            case CHROME:
                return initChromeDriver();
            case YANDEX:
                String driverPath = properties.getProperty("webdriver.chrome.driver");
                System.out.println("Yandex driver path = " + driverPath);
                System.setProperty("webdriver.chrome.driver", driverPath);
                return initYandexDriver();
            default:
                throw new RemoteException("Browser undefined");
        }
    }

    private static WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver initYandexDriver() {
        ChromeOptions options = new ChromeOptions();

        options.setBinary("C:\\Users\\Kharchenko Family\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

        return new ChromeDriver(options);
    }
}
