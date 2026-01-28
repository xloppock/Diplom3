package tests;

import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegistrationPage;
import pageobjects.ForgotPasswordPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Assert;
import org.junit.Test;

@Epic("Авторизация")
@Feature("Различные способы входа в аккаунт")
public class LoginTest extends BaseTest {

    @Test
    @Description("Успешный вход через кнопку 'Войти в аккаунт' на главной странице")
    @Story("Вход с главной страницы")
    public void testLoginFromMainPageButton() {
        openMainPage();

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginAccountButton();
        Assert.assertTrue("Должна открыться страница входа",
                loginPage.isLoginPageDisplayed());
    }

    @Test
    @Description("Успешный вход через кнопку 'Личный кабинет' на главной странице")
    @Story("Вход через личный кабинет")
    public void testLoginFromPersonalAccountButton() {
        openMainPage();

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickProfileButton();
        Assert.assertTrue("Должна открыться страница входа",
                loginPage.isLoginPageDisplayed());
    }

    @Test
    @Description("Успешный вход через форму регистрации")
    @Story("Вход с формы регистрации")
    public void testLoginFromRegistrationForm() {
        openPage("register");

        RegistrationPage registerPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue("Должна открыться страница регистрации",
                registerPage.isRegistrationPageDisplayed());

        registerPage.clickLoginLink();
        Assert.assertTrue("Должна открыться страница входа",
                loginPage.isLoginPageDisplayed());
    }

    @Test
    @Description("Успешный вход через форму восстановления пароля")
    @Story("Вход со страницы восстановления пароля")
    public void testLoginFromForgotPasswordForm() {
        openPage("forgot-password");

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue("Должна открыться страница восстановления пароля",
                forgotPasswordPage.isForgotPasswordPageDisplayed());

        forgotPasswordPage.clickLoginLink();
        Assert.assertTrue("Должна открыться страница входа",
                loginPage.isLoginPageDisplayed());
    }
}