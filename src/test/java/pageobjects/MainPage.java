package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By profileButton = By.xpath("//p[text()='Личный Кабинет']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginAccountButton)).click();
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }
}