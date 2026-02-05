package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    private final By nameInput = By.name("name");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.name("Пароль");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By passwordError = By.xpath("//p[contains(@class, 'input__error') and contains(text(), 'Некорректный')]");
    private final By loginLink = By.linkText("Войти");
    private final By registerHeader = By.xpath("//h2[text()='Регистрация']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести имя: {name}")
    public void enterName(String name) {
        closeModalIfPresent();
        sendKeysToElement(nameInput, name);
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

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        closeModalIfPresent();
        clickElement(registerButton);
    }

    @Step("Заполнить форму регистрации: имя={name}, email={email}, пароль={password}")
    public void fillRegisterForm(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        closeModalIfPresent();
        clickElement(loginLink);
    }

    @Step("Проверить наличие ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        closeModalIfPresent();
        return isElementVisible(passwordError);
    }

    @Step("Проверить, что страница регистрации открыта")
    public boolean isRegistrationPageDisplayed() {
        closeModalIfPresent();
        return isElementVisible(registerHeader);
    }
}
