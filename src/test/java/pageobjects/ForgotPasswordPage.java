package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private final By loginLink = By.linkText("Войти");
    private final By forgotPasswordHeader = By.xpath("//h2[text()='Восстановление пароля']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        closeModalIfPresent();
        clickElement(loginLink);
    }

    @Step("Проверить, что страница восстановления пароля открыта")
    public boolean isForgotPasswordPageDisplayed() {
        closeModalIfPresent();
        return isElementVisible(forgotPasswordHeader);

    }
}