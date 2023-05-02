package guru.qa;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverConditions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("WEB - тесты для демонстрации возможностей JUnit")
@Tag("WEB")
public class GoogleWebTest {

    @BeforeEach
    void setup() {
        Configuration.pageLoadStrategy = "eager";
        open("https://www.google.com/");
    }

    //дата провайдер
    @CsvSource(value = {
            "selenide | https://selenide.org",
            "JUnit | https://junit.org"
    },
            delimiter = '|')
//    @CsvSource(value = {
//            "selenide, https://selenide.org",
//            "JUnit, https://junit.org"
//    },

    // OR
//  @CsvFileSource(resources = "/successfulSearchTest.csv", delimiter = '|')
    @ParameterizedTest(name = "Для поискового запроса: {0} в выдаче присутствует url: {1}")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("SMOKE"),
            @Tag("WEB")
    })
    void successfulSearchTest(String searchQuery, String expectedUrl) {
        $("[name=q]").setValue(searchQuery).pressEnter();
        $("[id=search]").shouldHave(text(expectedUrl));
    }


    @ValueSource(strings = {
            "selenide",
            "junit"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} в нажимается кнопка 'Мне повезет'")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    @DisplayName("")
    void fartTest(String searchQuery) {
        $("[name=q]").setValue(searchQuery);
        $$("input[name='btnI']").filter(visible)
                .first()
                .click();
        //WebDriverConditions.currentFrameUrlContaining("selenide.org");
    }
}