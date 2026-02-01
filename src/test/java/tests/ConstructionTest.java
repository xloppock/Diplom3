package tests;

import org.junit.After;
import org.junit.Assert;
import pageobjects.ConstructorPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Epic("Конструктор")
@Feature("Переход по вкладкам конструктора")
public class ConstructionTest extends BaseTest {

    private ConstructorPage constructorPage;

    @Before
    public void setUpConstructorPage() {
        constructorPage = new ConstructorPage(driver);

        closeModalIfPresent();

        wait.until(ExpectedConditions.urlContains("stellarburgers"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    @Test
    @Description("Успешный переход к разделу 'Соусы'")
    @Story("Переключение вкладок")
    public void testSwitchToSaucesTab() {
        closeModalIfPresent();

        String initialTab = constructorPage.getActiveTabText();
        System.out.println("Начальная вкладка: " + initialTab);

        constructorPage.clickSaucesTab();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String activeTab = constructorPage.getActiveTabText();
        Assert.assertEquals("Соусы", activeTab);
    }

    @Test
    @Description("Успешный переход к разделу 'Начинки'")
    @Story("Переключение вкладок")
    public void testSwitchToFillingsTab() {
        closeModalIfPresent();

        constructorPage.clickFillingsTab();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String activeTab = constructorPage.getActiveTabText();
        Assert.assertEquals("Начинки", activeTab);
    }

    @Test
    @Description("Успешный переход к разделу 'Булки' после переключения на другие вкладки")
    @Story("Переключение вкладок")
    public void testSwitchToBunsTab() {
        closeModalIfPresent();

        String initialTab = constructorPage.getActiveTabText();
        System.out.println("Начальная вкладка: " + initialTab);

        constructorPage.clickSaucesTab();
        Assert.assertEquals("Соусы", constructorPage.getActiveTabText());

        constructorPage.clickBunsTab();

        int attempts = 0;
        while (attempts < 5) {
            try {
                constructorPage.waitForActiveTab("Булки");
                break;
            } catch (Exception e) {
                attempts++;
                System.out.println("Попытка " + attempts + " не удалась, жду...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        String activeTab = constructorPage.getActiveTabText();
        Assert.assertEquals("Булки", activeTab);
    }
}