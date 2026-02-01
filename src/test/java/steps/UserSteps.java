package steps;

import models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static tests.BaseTest.API_BASE_URL;

public class UserSteps {

    @Step("Создание пользователя")
    public User createUser() {
        String name = "User" + RandomStringUtils.randomNumeric(6);
        String email = "test" + RandomStringUtils.randomNumeric(6) + "@test.com";
        String password = RandomStringUtils.randomAlphanumeric(10);

        User user = new User(email, name, password);

        Map<String, String> body = new HashMap<>();
        body.put("email", user.getEmail());
        body.put("password", user.getPassword());
        body.put("name", user.getName());

        Response response = given()
                .header("Content-type", "application/json")
                .body(body)
                .post(API_BASE_URL + "/auth/register");

        response.then()
                .statusCode(200)
                .body("success", equalTo(true));

        user.setAccessToken(response.path("accessToken"));
        user.setRefreshToken(response.path("refreshToken"));

        return user;
    }

    @Step("Удаление пользователя")
    public void deleteUser(User user) {
        if (user != null && user.getAccessToken() != null) {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", user.getAccessToken())
                    .delete(API_BASE_URL + "/auth/user")
                    .then()
                    .statusCode(202)
                    .body("success", equalTo(true));
        }
    }
    }
