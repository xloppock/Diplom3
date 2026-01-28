package tests;

import org.junit.Assert;
import pageobjects.ConstructorPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Before;
import org.junit.Test;

@Epic("Конструктор")
@Feature("Переход по вкладкам конструктора")
public class ConstructionTest extends BaseTest {

    private ConstructorPage constructorPage;

    @Before
    public void setUpConstructorPage() {
        openMainPage();
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    @Description("Успешный переход к разделу 'Соусы'")
    @Story("Переключение вкладок")
    public void testSwitchToSaucesTab() {
        constructorPage.clickSaucesTab();
        String activeTab = constructorPage.getActiveTabText();
        Assert.assertEquals("Соусы", activeTab);
    }

    @Test
    @Description("Успешный переход к разделу 'Начинки'")
    @Story("Переключение вкладок")
    public void testSwitchToFillingsTab() {
        constructorPage.clickFillingsTab();
        String activeTab = constructorPage.getActiveTabText();
        Assert.assertEquals("Начинки", activeTab);
    }

    @Test
    @Description("Успешный переход к разделу 'Булки' после переключения на другие вкладки")
    @Story("Переключение вкладок")
    public void testSwitchToBunsTab() {
        constructorPage.clickSaucesTab();
        constructorPage.waitForActiveTab("Соусы");

        constructorPage.clickBunsTab();
        constructorPage.waitForActiveTab("Булки");

        String activeTab = constructorPage.getActiveTabText();
        Assert.assertEquals("Булки", activeTab);
    }
}