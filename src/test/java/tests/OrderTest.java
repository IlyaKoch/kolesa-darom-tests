package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;

public class OrderTest extends TestBase {

    @Test
    void createOrderTest(){
        Configuration.browser = MyFactory.class.getName();
        loginAs(credentials.userPhone(), credentials.userPassword());
        searchOnSite("Автомобильный аккумулятор Nomad Asia 50 Ач");
        $x("//div[@class='digi-product__price'][1]").click();
        $x("//span[contains(text(), 'Товар добавлен в корзину')]").shouldHave(Condition.text("Товар добавлен в корзину"));
        $x("//a[@class='kd-btn kd-btn_primary']").click();
    }
}