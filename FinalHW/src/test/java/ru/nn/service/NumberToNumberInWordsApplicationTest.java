package ru.nn.service;

import org.junit.jupiter.api.Test;
import ru.nn.api.service.IOService;
import ru.nn.domain.MeasureUnit;
import ru.nn.domain.RubleUnit;
import ru.nn.spy.IOServiceSpy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberToNumberInWordsApplicationTest {
    @Test
    void testRun() {
        List<String> expectedFlow = List.of("Введите целое число не больше 999999999:",
                "1345678900",
                "Введено число больше максимального",
                "Введите целое число не больше 999999999:",
                "-123",
                "Введено отрицательное число",
                "Введите целое число не больше 999999999:",
                "abcd",
                "Введено не число",
                "Введите целое число не больше 999999999:",
                "2345601",
                "два миллиона триста сорок пять тысяч шестьсот один рубль");
        List<String> actualFlow = new ArrayList<>();

        IOService ioServiceSpy = new IOServiceSpy(actualFlow);
        MeasureUnit rubleUnit = new RubleUnit();
        NumberToNumberInWordsApplication application = new NumberToNumberInWordsApplication(ioServiceSpy, rubleUnit);
        application.run();
        assertEquals(expectedFlow, actualFlow);
    }
}
