package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        System.setProperty("wdm.os", "WIN");
        System.setProperty("wdm.architecture", "64");

        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--window-size=1920,1080");

        if (browser.equals("yandex")) {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                String yandexPath = "C:\\Users\\" + System.getProperty("user.name") +
                        "\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
                options.setBinary(yandexPath);
                System.out.println("Using Yandex browser at: " + yandexPath);
            } else if (os.contains("mac")) {
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            } else {
                options.setBinary("/usr/bin/yandex-browser");
            }
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }
}