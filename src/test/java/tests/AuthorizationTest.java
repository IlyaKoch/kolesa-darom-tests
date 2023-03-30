package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Feature("Authorization")
public class AuthorizationTest extends TestBase {

    @Test
    @AllureId("16231")
    @DisplayName("Successful login")
    void positiveAuthorizationTest() {
        Configuration.startMaximized = true;

        step("Open main page", () -> open(""));

        step("Click login icon", () -> $x("//div[contains(text(), 'Войти')]").click());
        step("Switch on tab Login with password", () -> $x("//span[contains(text(), 'Войти по паролю')]").click());
        step("Enter phone number and password", () -> {
            $("#phoneNum").setValue(credentials.userPhone());
            $("#password").setValue(credentials.userPassword());
        });
        step("Click on Log in button", () -> $x("//span[contains(text(), 'Войти')]/ancestor::button").click());

        step("Check that user is logged in", () -> {
            $x("//a[@href=\"/personal/\"]").click();
            $x("//div[@class=\"user-widget__name\"]").shouldHave(Condition.text("Кочетков Илья"));
        });
    }

    @Test
    @AllureId("16233")
    @DisplayName("Negative Login with wrong password")
    void negativeAuthorizationTestWithIncorrectPassword() {
        Configuration.startMaximized = true;

        step("Open main page", () -> open(""));

        step("Click login icon", () -> $x("//div[contains(text(), 'Войти')]").click());
        step("Switch on tab Login with password", () -> $x("//span[contains(text(), 'Войти по паролю')]").click());
        step("Enter phone number and password", () -> {
            $("#phoneNum").setValue(credentials.userPhone());
            $("#password").setValue("IncorrectPass");
        });
        step("Click on Log in button", () -> $x("//span[contains(text(), 'Войти')]/ancestor::button").click());

        step("Check error", () -> {
            $x("//div[contains(text(), 'Неверный логин или пароль')]").shouldHave(Condition.text("Неверный логин или пароль"));
        });
    }
}