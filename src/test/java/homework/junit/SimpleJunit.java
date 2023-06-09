package homework.junit;

import com.google.common.reflect.ClassPath;
import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

//Опционально (advanced!).
// Доработать класс http://github.com/qa-guru/qa_guru_13_6_junit/blob/master/src/test/java/junit/SimpleJUnit.java
// из репо для того, чтоб он поддерживал выполнение методов @BeforeEach / @AfterEach, @BeforeAll/AfterAll
// (надо почитать о том, как вызываются статические методы через invoke() и подумать, в какие места циклов это вставлять)

public class SimpleJunit {
    public static void main(String[] args) throws Exception {
        //метод main запускатся при нажатии на зеленую кнопчку запуска тестов
        // Точка входа в любой java программе является метод main, в тестах он встроен в junit, который
        // Достает все классы из src test java
        Set<? extends Class<?>> classes = ClassPath.from(ClassLoader.getSystemClassLoader()) //ClassLoader - ищет все классы в нашей  прогоммае (getSystemClassLoader) и все файлы в ресурсках
                .getAllClasses()
                .stream()
                .filter(classInfo -> classInfo.getPackageName().contains("simpleTests"))
                .map(ClassPath.ClassInfo::load)
                .collect(Collectors.toSet());
        for (Class<?> aClass : classes) {
            runBeforeAll(aClass);
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) { //проходим по всем методам класса
                // Смотрит в метаинформации классов нет ли там @Test
                Test testAnnotation = method.getAnnotation(Test.class);
                if (testAnnotation != null) {
                    // Если есть, то запускает этот метод
                    method.setAccessible(true);
                    try {
                        runBeforeEach(aClass);
                        method.invoke(aClass.getDeclaredConstructor().newInstance());
                    } catch (Throwable t) {
                        // Если метод выбросил исключение с типом AssertionError - желтый тест
                        if (AssertionError.class.isAssignableFrom(t.getCause().getClass())) {
                            System.out.println("Тест: " + method.getName() + " желтый");
                        } else {
                            // Если метод выбросил исключение с любым другим - красный тест
                            System.out.println("Тест: " + method.getName() + " красный");
                        }
                        continue;
                    }
                    // Если метод успешно выполнился - зеленый тест
                    System.out.println("Тест :" + method.getName() + " зеленый");
                    runAfterEach(aClass);
                }
            }
            runAfterAll(aClass);
        }
    }

    public static void runBeforeAll(Class<?> aClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            BeforeAll beforeAllAnnotation = method.getAnnotation(BeforeAll.class);
            if (beforeAllAnnotation != null) {
                method.setAccessible(true);
                method.invoke(aClass.getDeclaredConstructor().newInstance());
            }
        }
    }

    public static void runAfterAll(Class<?> aClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            AfterAll afterAllAnnotation = method.getAnnotation(AfterAll.class);
            if (afterAllAnnotation != null) {
                method.setAccessible(true);
                method.invoke(aClass.getDeclaredConstructor().newInstance());
            }
        }
    }

    public static void runBeforeEach(Class<?> aClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            BeforeEach beforeEachAnnotation = method.getAnnotation(BeforeEach.class);
            if (beforeEachAnnotation != null) {
                method.setAccessible(true);
                method.invoke(aClass.getDeclaredConstructor().newInstance());
            }
        }
    }

    public static void runAfterEach(Class<?> aClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            AfterEach afterEachEachAnnotation = method.getAnnotation(AfterEach.class);
            if (afterEachEachAnnotation != null) {
                method.setAccessible(true);
                method.invoke(aClass.getDeclaredConstructor().newInstance());
            }
        }
    }
}
