package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.name("Пароль");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By loginHeader = By.xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        closeModalIfPresent();
        sendKeysToElement(emailInput, email);
    }

    @Step("Ввести пароль: {password}")
    public void enterPassword(String password) {
        closeModalIfPresent();
        sendKeysToElement(passwordInput, password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        closeModalIfPresent();
        clickElement(loginButton);
    }

    @Step("Выполнить вход с email: {email} и паролем: {password}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Проверить, что страница входа открыта")
    public boolean isLoginPageDisplayed() {
        closeModalIfPresent();
        return isElementVisible(loginHeader);
    }
}