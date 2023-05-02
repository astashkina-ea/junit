package homework.tests;

import homework.page.MainPage;
import homework.page.component.RatingPopupComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("Оценка веб сайта")
public class RatingWebsiteTests extends TestBase {

    private MainPage mainPage = new MainPage();
    private RatingPopupComponent ratingPopupComponent = new RatingPopupComponent();

    @CsvSource(value = {
            "Ужасно, Вы чем-то расстроены? Пожалуйста, поделитесь с нами!",
            "Нормально, Что можно улучшить?"
    })
    @ParameterizedTest(name = "При выборе оценки {0} отображается заголовок {1}")
    @Tags({
            @Tag("MEDIUM"),
            @Tag("WEB")
    })
    void verifyTitleRatingModalTest1(String rating, String secondTitle) {
        open("/");
        mainPage.openMainPage()
                .pressRatingBtn();

        ratingPopupComponent.pressRating(rating)
                        .verifySecondTitle(secondTitle);
    }

    @CsvFileSource(resources = "/rate.csv")
    @ParameterizedTest(name = "При выборе оценки {0} отображается заголовок {1}")
    @Tags({
            @Tag("MEDIUM"),
            @Tag("WEB")
    })
    void verifyTitleRatingModalTest2(String rating, String secondTitle) {
        open("/");
        mainPage.openMainPage()
                .pressRatingBtn();

        ratingPopupComponent.pressRating(rating)
                .verifySecondTitle(secondTitle);;
    }
}