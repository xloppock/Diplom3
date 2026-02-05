package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By profileButton = By.xpath(".//p[contains(@class, 'AppHeader_header__linkText') and contains(@class, 'ml-2') and text()='Личный Кабинет']");
    private final By mainHeaderLocator = By.xpath("//h1[contains(text(),'Соберите бургер')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginAccountButton() {
        closeModalIfPresent();
        clickElement(loginAccountButton);
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickProfileButton() {
        closeModalIfPresent();
        clickElement(profileButton);
    }

    @Step("Проверка отображения главной страницы")
    public boolean isMainPageDisplayed() {
        closeModalIfPresent();
        return isElementVisible(mainHeaderLocator);
    }

}