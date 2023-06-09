package lesson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//выключает группу тестов
@Disabled //@Disabled("причина выключения теста")
//название группы тестов (видно в аллюре)
@DisplayName("")
public class SimpleTest {

    @Test
    //выключает тест
    @Disabled //@Disabled("причина выключения теста")
    //название теста (видно в аллюре)
    @DisplayName("")
    void simpleTest() {
        Assertions.assertEquals(3, 2 + 1, "Результат дб 3");
    }
    
    // assertEquals() и assertNotEquals() помогают убедиться в том, что фактическое значение идентично с ожидаемым и наоборот.

    // assertTrue()
    // Если утверждение в скобках верно, то тест пройдет.

    // assertFalse()
    // Если утверждение в скобка ложно, то тест пройдет.

//    assertAll()
//    Позволяет проверить сразу несколько утверждений. Даже если одно из них упадет, то все равно будут выполнены все до самого конца, а система покажет те, которые упали.
//
//    Пример:
//
//            Assertions.assertAll(
//            () -> Assertions.assertFalse(10 < 2),
//            () -> Assertions.assertTrue(5 > 2),
//            () -> Assertions.assertEquals("Good", "Good")
//            );
}
