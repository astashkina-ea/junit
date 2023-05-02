package guru.qa;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import guru.qa.domain.Locale;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("WEB - тесты для демонстрации возможностей JUnit")
public class SelenideWebTest {

    @BeforeEach
    void setup() {
        Configuration.pageLoadStrategy = "eager";
        open("https://ru.selenide.org/");

    }

    static Stream<Arguments> selenideLocaleTestDataProvider() { //должен называться как тест, но если не хотим дублировать, то в анатации @MethodSource в ("") указывается название дата провайдера
        return Stream.of(
                Arguments.of(
                        Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")
                ),
                Arguments.of(
                        Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы")
                )
        );
    }

    @MethodSource("selenideLocaleTestDataProvider") //заменяет все остальыне дата провайдеры
    @ParameterizedTest
    void selenideLocaleTest(Locale siteLocale, List<String> expectedButtons) {
        $$("#languages a").find(text(siteLocale.name())).click();

        $$(".main-menu-pages a").filter(visible).shouldHave(CollectionCondition.texts(expectedButtons));
    }
}