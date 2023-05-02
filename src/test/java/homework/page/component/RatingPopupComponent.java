package homework.page.component;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RatingPopupComponent {

    private SelenideElement secondTitle = $$("form h2").last();

    public RatingPopupComponent pressRating(String rating) {
        $(String.format("[data-title='%s'", rating)).click();
        return this;
    }

    public RatingPopupComponent verifySecondTitle(String rating) {
        secondTitle.shouldHave(text(rating));
        return this;
    }
}