package ru.nn.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nn.domain.Gender;
import ru.nn.domain.MeasureUnit;
import ru.nn.domain.RubleUnit;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberToNumberInWordsConverterTest {
    @ParameterizedTest
    @CsvSource({"0, 'ноль рублей'",
            "1, 'один рубль'",
            "10, 'десять рублей'",
            "402, 'четыреста два рубля'",
            "2345, 'две тысячи триста сорок пять рублей'",
            "50000, 'пятьдесят тысяч рублей'",
            "601411, 'шестьсот одна тысяча четыреста одиннадцать рублей'",
            "1234567, 'один миллион двести тридцать четыре тысячи пятьсот шестьдесят семь рублей'",
            "19234567, 'девятнадцать миллионов двести тридцать четыре тысячи пятьсот шестьдесят семь рублей'",
            "102234567, 'сто два миллиона двести тридцать четыре тысячи пятьсот шестьдесят семь рублей'"})
    void testConvertNumberToNumberInWordsInRubles(int number, String numberInWords) {
        MeasureUnit rubleUnit = new RubleUnit();
        try {
            assertEquals(numberInWords, NumberToNumberInWordsConverter.convertNumber(number, rubleUnit));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConvertNumberToNumberInWordsWithNumberGreaterThenMaximum() {
        MeasureUnit rubleUnit = new RubleUnit();
        assertThrows(Exception.class, () -> NumberToNumberInWordsConverter.convertNumber(1234567890, rubleUnit));
    }

    @ParameterizedTest
    @MethodSource("generateDataForSplitNumberStringIntoDigitGroupStrings")
    void testSplitNumberStringIntoDigitGroupStrings(String numberAsString, List<String> groups) {
        try {
            Method method = NumberToNumberInWordsConverter.class.getDeclaredMethod("splitNumberStringIntoDigitGroupStrings",
                    String.class, int.class);
            method.setAccessible(true);
            assertEquals(groups, method.invoke(NumberToNumberInWordsConverter.class, numberAsString, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Stream<Arguments> generateDataForSplitNumberStringIntoDigitGroupStrings() {
        return Stream.of(
                Arguments.of("", Arrays.asList()),
                Arguments.of("1", Arrays.asList("1")),
                Arguments.of("12", Arrays.asList("12")),
                Arguments.of("123", Arrays.asList("123")),
                Arguments.of("1234", Arrays.asList("234", "1")),
                Arguments.of("12345", Arrays.asList("345", "12")),
                Arguments.of("123456", Arrays.asList("456", "123")),
                Arguments.of("1234567", Arrays.asList("567", "234", "1")),
                Arguments.of("12345678", Arrays.asList("678", "345", "12")),
                Arguments.of("123456789", Arrays.asList("789", "456", "123"))
        );
    }

    @ParameterizedTest
    @CsvSource({"'321', 'триста двадцать один миллион'",
            "'52', 'пятьдесят два миллиона'",
            "'10', 'десять миллионов'",
            "'', ''"})
    void testGetMillionInWords(String digitGroup, String millionInWords) {
        try {
            Method method = NumberToNumberInWordsConverter.class.getDeclaredMethod("getMillionthPartInWords", String.class);
            method.setAccessible(true);
            assertEquals(millionInWords, method.invoke(NumberToNumberInWordsConverter.class, digitGroup));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'321', 'триста двадцать одна тысяча'",
            "'52', 'пятьдесят две тысячи'",
            "'10', 'десять тысяч'",
            "'', ''"})
    void testGetThousandInWords(String digitGroup, String thousandInWords) {
        try {
            Method method = NumberToNumberInWordsConverter.class.getDeclaredMethod("getThousandthPartInWords", String.class);
            method.setAccessible(true);
            assertEquals(thousandInWords, method.invoke(NumberToNumberInWordsConverter.class, digitGroup));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'321', 'триста двадцать один рубль'",
            "'52', 'пятьдесят два рубля'",
            "'10', 'десять рублей'",
            "'0', 'рублей'"})
    void testGetHundredInWords(String digitGroup, String hundredInWords) {
        try {
            Method method = NumberToNumberInWordsConverter.class.getDeclaredMethod("getHundredthPartInWords", String.class, MeasureUnit.class);
            method.setAccessible(true);
            MeasureUnit rubleUnit = new RubleUnit();
            assertEquals(hundredInWords, method.invoke(NumberToNumberInWordsConverter.class, digitGroup, rubleUnit));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'0', ''",
            "'1', 'один'",
            "'22', 'двадцать два'",
            "'325', 'триста двадцать пять'"})
    void testGetHundredInMasculineWordsWithoutMeasureUnit(String number, String hundredInWords) {
        try {
            Method method = NumberToNumberInWordsConverter.class.getDeclaredMethod("getHundredthPartInWordsWithoutMeasureUnit",
                    String.class, Gender.class);
            method.setAccessible(true);
            assertEquals(hundredInWords, method.invoke(NumberToNumberInWordsConverter.class, number, Gender.MASCULINE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'0', ''",
            "'1', 'одна'",
            "'22', 'двадцать две'",
            "'325', 'триста двадцать пять'"})
    void testGetHundredInFeminineWordsWithoutMeasureUnit(String number, String hundredInWords) {
        try {
            Method method = NumberToNumberInWordsConverter.class.getDeclaredMethod("getHundredthPartInWordsWithoutMeasureUnit",
                    String.class, Gender.class);
            method.setAccessible(true);
            assertEquals(hundredInWords, method.invoke(NumberToNumberInWordsConverter.class, number, Gender.FEMININE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'0', ''",
            "'1', 'одно'",
            "'22', 'двадцать два'",
            "'325', 'триста двадцать пять'"})
    void testGetHundredInNeuterWordsWithoutMeasureUnit(String number, String hundredInWords) {
        try {
            Method method = NumberToNumberInWordsConverter.class.getDeclaredMethod("getHundredthPartInWordsWithoutMeasureUnit",
                    String.class, Gender.class);
            method.setAccessible(true);
            assertEquals(hundredInWords, method.invoke(NumberToNumberInWordsConverter.class, number, Gender.NEUTER));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
