package tests;

import utils.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    protected WebDriver driver;
    protected final String BASE_URL = "https://stellarburgers.education-services.ru/";

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void openPage(String path) {
        driver.get(BASE_URL + path);
    }

    protected void openMainPage() {
        driver.get(BASE_URL);
    }
}