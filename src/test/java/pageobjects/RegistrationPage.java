package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInput = By.name("name");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.name("Пароль");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By passwordError = By.xpath("//p[contains(@class, 'input__error') and contains(text(), 'Некорректный')]");
    private final By loginLink = By.linkText("Войти");
    private final By registerHeader = By.xpath("//h2[text()='Регистрация']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввести имя: {name}")
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    @Step("Ввести пароль: {password}")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
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
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Проверить наличие ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Проверить, что страница регистрации открыта")
    public boolean isRegistrationPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(registerHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}