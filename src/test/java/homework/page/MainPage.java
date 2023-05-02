package homework.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    private SelenideElement ratingBtn = $("[data-ignore-node]"),
            catalogBtn = $("[data-qa-catalogue-button]");

    public MainPage openMainPage() {
        open("/");
        return this;
    }

    public MainPage pressRatingBtn() {
        ratingBtn.click();
        return this;
    }

    public MainPage pressCatalogBtn() {
        catalogBtn.click();
        return this;
    }
}