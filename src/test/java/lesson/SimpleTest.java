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
}