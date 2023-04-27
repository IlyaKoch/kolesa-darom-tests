package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class PersonalCabinetTest extends TestBase {

    @Test
    void addToCartTest(){
        openMainPage();
        selectLocation("Самара");
        searchOnSite("Автомобильный аккумулятор Nomad Asia 50 Ач");
        step("Click add to cart on item", () -> $x("//div[@class='digi-product__price'][1]").click());
        step("Check that item has been added to cart", () -> $x("//span[contains(text(), 'Товар добавлен в корзину')]").shouldHave(Condition.text("Товар добавлен в корзину")));
    }

    @Test
    void addToFavoritesTest(){
        openMainPage();
        selectLocation("Самара");
        searchOnSite("Автомобильный аккумулятор Nomad Asia 50 Ач");
        $x("//div[@class='digi-product__button-favorite-add'][1]").click();
        $x("//span[contains(text(), 'Избранное')]/../div").shouldHave(Condition.text("1"));
    }
}