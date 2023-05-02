package homework.page.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogComponent {

    private ElementsCollection titlesCatalog = $$("[data-qa='catalogue-item-name']");

    private SelenideElement titleCatalog = $("[data-qa='catalogue-title']");

    public CatalogComponent selectSubCatalog(String subcatalog) {
        titlesCatalog.findBy(text(subcatalog)).click();
        return this;
    }

    public CatalogComponent verifyTitleCatalog(String catalog) {
        titleCatalog.shouldHave(text(catalog));
        return this;
    }

    public CatalogComponent verifyTitlesCatalog(List<String> catalog) {
        titlesCatalog.filter(visible).shouldHave(texts(catalog));
        return this;
    }
}
