package tests;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.webdriver.ChromeDriverFactory;
import config.CredentialAppConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static java.util.Arrays.asList;

abstract public class TestBase {

    public static CredentialAppConfig credentials =
            ConfigFactory.create(CredentialAppConfig.class);

    public void openMainPage(){
        step("Open main page", () -> open(""));
    }

    public void loginAs(String phoneNum, String password) {
        step("Login on site", () -> {
            openMainPage();
            $x("//div[contains(text(), 'Войти')]").click();
            $x("//span[contains(text(), 'Войти по паролю')]").click();
            $("#phoneNum").setValue(phoneNum);
            $("#password").setValue(password);
            $x("//span[contains(text(), 'Войти')]/ancestor::button").click();
        });
    }

    public void selectLocation(String city){
        step("Select city " + city, () -> {
            $("span.header-city__inner").click();
            $("#city-search").setValue(city);
            $("#autosuggest__results_item-0").click();
        });
    }

    public void openCatalogSection(String sectionName){
        step("Select catalog section " + sectionName, () -> {
            $x("//span[contains(text(), 'Каталог')]").click();
            SelenideElement element = $x("//li[@class='fullmenu__nested-item']//a[contains(text(), '" + sectionName + "')]");
            element.click();
        });
    }

    public void searchOnSite(String itemName){
        step("Search " + itemName, () -> {
            $x("//input[@name=\"q\"]").setValue(itemName).pressEnter();
        });
    }

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://www.kolesa-darom.ru/";

        //        Настройки для selenoid
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("enableVideo", true);
//        Configuration.browserCapabilities = capabilities;
    }

//    @AfterEach
//    public void tearDown() {
//        Attach.screenshotAs("Last screenshot");
//        Attach.pageSource();
//        Attach.browserConsoleLogs();
//        Attach.addVideo();
//    }

    //решение проблемы зависающих процессов хрома
    protected static class MyFactory extends ChromeDriverFactory {
        @Override
        protected List<String> createChromeArguments(Config config, Browser browser) {
            return asList("--proxy-bypass-list=<-loopback>"); // no "--no-sandbox" here
        }
    }
}