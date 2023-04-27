package tests.serviceTests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class TireServiceTest extends ServiceBaseTest {

    void chooseTireServiceAddress() {
        step("Choose service address", () -> {
            $x("//button/span[contains(text(), 'Выбрать сервис')]").shouldBe(Condition.enabled).click();
            $(".wrap.wrap-map").click();
            $x("//section[@data-scroll-list-container]/div[1]").click();
            $(".button.confirm.primary").click();
        });
    }

    @ValueSource(strings = {
            "Казань",
            "Самара",
            "Саратов"
    })
    @ParameterizedTest(name = "Check search results for {0}")
    void checkingAbilityToSelectTireServiceAddress(String city) {
        openMainPage();
        selectLocation(city);
        openCatalogSection("Шиномонтаж");
        chooseTireServiceAddress();
    }



    @Test
    void checkingAbilityToSelectTireServiceAddressFromSaintP() {
        openMainPage();
        selectLocation("Санкт-Петербург");
        step("Click on tire service tab", () -> $x("//a[contains(text(), 'Шиномонтаж')]").click());
        chooseTireServiceAddress();
    }

    @Test
    void openTireServiceViaCatalog() {
        openMainPage();
        openCatalogSection("Шиномонтаж");
        chooseTireServiceAddress();
    }
}