package homework.tests;

import homework.page.MainPage;
import homework.page.component.CatalogComponent;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

public class SubcatalogTests extends TestBase{

    private MainPage mainPage = new MainPage();
    private CatalogComponent catalogComponent = new CatalogComponent();

    @ValueSource(strings = {
            "Инструменты",
            "Краски"
    })
    @ParameterizedTest(name = "Отображение заголовка {0} при переходе в каталог {0}")
    @Tags({
            @Tag("MEDIUM"),
            @Tag("WEB")
    })
    void verifyTitleCatalogTest(String catalog) {
        mainPage.openMainPage()
                .pressCatalogBtn();

        catalogComponent.selectSubCatalog(catalog)
                .verifyTitleCatalog(catalog);
    }

    static Stream<Arguments> titlesSubCatalogTestDataProvider() {
        return Stream.of(
                Arguments.of(
                        "Инструменты", List.of("Электроинструменты", "Аксессуары для электроинструмента", "Ручной инструмент", "Минимойки и строительные пылесосы", "Оборудование для мастерской", "Сварочное оборудование", "Организация рабочего места", "Средства индивидуальной защиты", "Одежда и обувь защитная", "Измерительно-разметочный инструмент", "Фиксирующий инструмент", "Штукатурно-отделочные инструменты", "Специнструменты для ремонта", "Профессиональные инструменты и крепеж", "Автотовары")
                ),
                Arguments.of(
                        "Краски", List.of("Краски для внутренних работ", "Эмали", "Краски для наружных работ", "Покрытия для дерева", "Подготовка поверхности к покраске и штукатурке", "Инструменты для покраски", "Пены, герметики и уплотнители", "Клеи", "Малярные и упаковочные ленты", "Бытовая химия", "Хозяйственные товары", "Проекты по покраске")
                )
        );
    }
    @MethodSource("titlesSubCatalogTestDataProvider")
    @ParameterizedTest(name = "Отображение подкаталогов {1} каталога {0}")
    void verifyTitlesSubCatalogTest(String catalog, List<String> subCatalogs) {
        mainPage.openMainPage()
                .pressCatalogBtn();
        catalogComponent.selectSubCatalog(catalog)
                .verifyTitlesCatalog(subCatalogs);
    }
}
