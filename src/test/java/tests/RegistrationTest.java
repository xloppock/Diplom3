package tests;

import pages.LoginPage;
import pages.RegistrationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

@Epic("Регистрация")
@Feature("Форма регистрации пользователя")
public class RegistrationTest extends BaseTest {

    @Test
    @Description("Успешная регистрация с валидными данными и переход на страницу входа")
    @Story("Успешная регистрация")
    public void testSuccessfulRegistrationRedirectsToLoginPage() {
        openPage("register");

        RegistrationPage registerPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        String name = RandomStringUtils.randomAlphabetic(8);
        String email = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphanumeric(8);

        registerPage.fillRegisterForm(name, email, password);

        Assert.assertTrue("После регистрации ожидался переход на страницу входа",
                loginPage.isLoginPageDisplayed());
    }

    @Test
    @Description("Появляется ошибка при регистрации с паролем меньше 6 символов")
    @Story("Некорректная регистрация")
    public void testRegisterWithShortPasswordShowsError() {
        openPage("register");

        RegistrationPage registerPage = new RegistrationPage(driver);

        String name = RandomStringUtils.randomAlphabetic(8);
        String email = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
        String shortPassword = "12345"; // Меньше 6 символов

        registerPage.fillRegisterForm(name, email, shortPassword);

        Assert.assertTrue("Ожидалась ошибка 'Некорректный пароль'",
                registerPage.isPasswordErrorDisplayed());
    }
}