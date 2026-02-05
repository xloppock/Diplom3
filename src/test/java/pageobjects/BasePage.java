package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");
    private final By modalCloseButton = By.xpath("//button[contains(@class, 'Modal_modal__close_icon__7x3rH')]");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected boolean isElementVisible(By locator) {
        try {
            return waitForElementVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void clickElement(By locator) {
        waitForElementClickable(locator).click();
    }

    protected void sendKeysToElement(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getElementText(By locator) {
        return waitForElementVisible(locator).getText();
    }

    protected void closeModalIfPresent() {
        try {
            if (isElementVisible(modalOverlay)) {
                waitForElementClickable(modalCloseButton);

                if (isElementVisible(modalCloseButton)) {
                    clickElement(modalCloseButton);
                } else {
                    clickElement(modalOverlay);
                }

                waitForElementToDisappear(modalOverlay);
            }
        } catch (Exception e) {
        }
    }
}