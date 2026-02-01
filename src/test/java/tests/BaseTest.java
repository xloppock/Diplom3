package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public static final String UI_BASE_URL = "https://stellarburgers.education-services.ru/";
    public static final String API_BASE_URL = "https://stellarburgers.education-services.ru/api";

    @Before
    public void setUp() {
        initializeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        openMainPage();
    }

    protected void initializeDriver() {
        driver = DriverFactory.createDriver();
        if (driver == null) {
            throw new RuntimeException("WebDriver is null!");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void tearDown() {
        // Закрываем драйвер после каждого теста
        closeDriver();
    }

    protected void closeDriver() {
        if (driver != null) {
            try {
                System.out.println("Closing WebDriver...");
                driver.quit();
                System.out.println("WebDriver closed successfully");
            } catch (Exception e) {
                System.out.println("Error closing driver: " + e.getMessage());
            }
        }
    }

    protected void openPage(String path) {
        if (driver == null) {
            initializeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

        String url = UI_BASE_URL + (path.startsWith("/") ? path.substring(1) : path);

        try {
            driver.get(url);

            if (wait == null) {
                wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            }

            wait.until(d -> {
                String readyState = (String) ((org.openqa.selenium.JavascriptExecutor) d)
                        .executeScript("return document.readyState");
                return "complete".equals(readyState);
            });


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void openMainPage() {
        openPage("");
    }

    protected void closeModalIfPresent() {
        try {
            WebDriverWait localWait = wait != null ? wait : new WebDriverWait(driver, Duration.ofSeconds(5));

            By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");
            By modalCloseButton = By.xpath("//button[contains(@class, 'Modal_modal__close_icon__7x3rH')]");

            if (driver.findElements(modalOverlay).size() > 0) {

                // Даем время для анимации
                Thread.sleep(300);

                if (driver.findElements(modalCloseButton).size() > 0) {
                    driver.findElement(modalCloseButton).click();
                } else {
                    driver.findElement(modalOverlay).click();
                    System.out.println("Clicked overlay");
                }

                localWait.until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
                Thread.sleep(500);
            }
        } catch (Exception e) {

        }
    }
}