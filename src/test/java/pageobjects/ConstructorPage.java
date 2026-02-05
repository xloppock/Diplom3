package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConstructorPage extends BasePage {

    private final By bunsTab = By.xpath("//span[text()='Булки']/ancestor::div[contains(@class,'tab')]");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/ancestor::div[contains(@class,'tab')]");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/ancestor::div[contains(@class,'tab')]");
    private final By activeTab = By.cssSelector("div.tab_tab_type_current__2BEPc span");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Кликнуть по вкладке 'Булки'")
    public void clickBunsTab() {
        closeModalIfPresent();
        clickElement(bunsTab);
        waitForActiveTab("Булки");
    }

    @Step("Кликнуть по вкладке 'Соусы'")
    public void clickSaucesTab() {
        closeModalIfPresent();
        clickElement(saucesTab);
        waitForActiveTab("Соусы");
    }

    @Step("Кликнуть по вкладке 'Начинки'")
    public void clickFillingsTab() {
        closeModalIfPresent();
        clickElement(fillingsTab);
        closeModalIfPresent();
        waitForActiveTab("Начинки");
    }

    @Step("Получить текст активной вкладки")
    public String getActiveTabText() {
        closeModalIfPresent();
        return getElementText(activeTab);
    }

    @Step("Ожидать, что активная вкладка будет с текстом: {expectedText}")
    public void waitForActiveTab(String expectedText) {
        wait.until(ExpectedConditions.textToBe(activeTab, expectedText));
    }
}