package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginLink = By.linkText("Войти");
    private final By forgotPasswordHeader = By.xpath("//h2[text()='Восстановление пароля']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Проверить, что страница восстановления пароля открыта")
    public boolean isForgotPasswordPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}