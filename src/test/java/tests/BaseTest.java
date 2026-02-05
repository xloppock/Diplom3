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

    public static final String UI_BASE_URL = "https://stellarburgers.education-services.ru/";
    public static final String API_BASE_URL = "https://stellarburgers.education-services.ru/api";


    @Before
    public void setUp() {
        initializeDriver();
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
        closeDriver();
    }

    protected void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Error closing driver: " + e.getMessage());
            }
        }
    }

    protected void openPage(String path) {
        String url = UI_BASE_URL + (path.startsWith("/") ? path.substring(1) : path);
        driver.get(url);
    }

    protected void openMainPage() {
        openPage("");
    }

}