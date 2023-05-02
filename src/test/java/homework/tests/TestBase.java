package homework.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://leroymerlin.ru/";
        Configuration.pageLoadStrategy = "eager";
    }
}