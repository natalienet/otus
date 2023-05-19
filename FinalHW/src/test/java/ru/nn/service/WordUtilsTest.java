package ru.nn.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.nn.domain.Gender;
import ru.nn.domain.MeasureUnit;
import ru.nn.domain.RubleUnit;
import ru.nn.domain.ThousandUnit;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordUtilsTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "21", "171"})
    void getRubleStringInSingular(String number) {
        MeasureUnit rubleUnit = new RubleUnit();
        assertEquals("рубль", WordUtils.getMeasureUnitStringForNumber(number, rubleUnit));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "5", "9", "11"})
    void getRubleStringInPlural(String number) {
        MeasureUnit rubleUnit = new RubleUnit();
        assertEquals("рублей", WordUtils.getMeasureUnitStringForNumber(number, rubleUnit));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "4", "23"})
    void getRubleStringInSingularAndGenitiveCase(String number) {
        MeasureUnit rubleUnit = new RubleUnit();
        assertEquals("рубля", WordUtils.getMeasureUnitStringForNumber(number, rubleUnit));
    }

    @Test
    void testGetMeasureUnitStringForNumberWithNullMeasureUnit() {
        assertThrows(NullPointerException.class, () -> WordUtils.getMeasureUnitStringForNumber("123", null));
    }

    @Test
    void testGetMeasureUnitStringForNumberWithEmptyNumberString() {
        MeasureUnit thousandUnit = new ThousandUnit();
        assertEquals("", WordUtils.getMeasureUnitStringForNumber("", thousandUnit));
    }

    @ParameterizedTest
    @CsvSource({"'00012', '12'",
            "'00000', ''",
            "'213', '213'"})
    void testTrimStartingZeros(String numberAsString, String numberAfterTrim) {
        try {
            Method method = WordUtils.class.getDeclaredMethod("trimStartingZeros", String.class);
            method.setAccessible(true);
            assertEquals(numberAfterTrim, method.invoke(WordUtils.class, numberAsString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'321', 'триста двадцать один'",
            "'22', 'двадцать два'",
            "'11', 'одиннадцать'",
            "'1', 'один'",
            "'300', 'триста'",
            "'320', 'триста двадцать'"})
    void testGetNumberInMasculineWords(String numberAsString, String numberInWords) {
        assertEquals(numberInWords, WordUtils.getNumberInWords(numberAsString, Gender.MASCULINE));
    }

    @ParameterizedTest
    @CsvSource({"'321', 'триста двадцать одна'",
            "'22', 'двадцать две'",
            "'11', 'одиннадцать'",
            "'1', 'одна'",
            "'300', 'триста'",
            "'320', 'триста двадцать'"})
    void testGetNumberInFeminineWords(String numberAsString, String numberInWords) {
        assertEquals(numberInWords, WordUtils.getNumberInWords(numberAsString, Gender.FEMININE));
    }

    @ParameterizedTest
    @CsvSource({"'321', 'триста двадцать одно'",
            "'22', 'двадцать два'",
            "'11', 'одиннадцать'",
            "'1', 'одно'",
            "'300', 'триста'",
            "'320', 'триста двадцать'"})
    void testGetNumberInNeuterWords(String numberAsString, String numberInWords) {
        assertEquals(numberInWords, WordUtils.getNumberInWords(numberAsString, Gender.NEUTER));
    }

    @ParameterizedTest
    @CsvSource({"'213', 0, 2",
            "'213', 1, 1",
            "'213', 2, 3"})
    void testGetDigitFromCharAtPosition(String numberAsString, int position, int digit) {
        try {
            Method method = WordUtils.class.getDeclaredMethod("getDigitFromCharAtPosition", String.class, int.class);
            method.setAccessible(true);
            assertEquals(digit, method.invoke(WordUtils.class, numberAsString, position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'123', 0, 123",
            "'123', 1, 23",
            "'123', 2, 3"})
    void testGetNumberFromSubstringAtPosition(String numberAsString, int position, int digit) {
        try {
            Method method = WordUtils.class.getDeclaredMethod("getNumberFromSubstringAtPosition", String.class, int.class);
            method.setAccessible(true);
            assertEquals(digit, method.invoke(WordUtils.class, numberAsString, position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'213', 1, true",
            "'213', 2, false"})
    void testCheckDigitAtPositionEqualsOne(String number, int position, boolean result) {
        try {
            Method method = WordUtils.class.getDeclaredMethod("checkDigitAtPositionEqualsOne", String.class, int.class);
            method.setAccessible(true);
            assertEquals(result, method.invoke(WordUtils.class, number, position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'121', 'один'",
            "'122', 'два'",
            "'123', 'три'"})
    void testGetDigitInMasculineWords(String number, String digitInWords) {
        try {
            Method method = WordUtils.class.getDeclaredMethod("getDigitInWords", String.class, Gender.class);
            method.setAccessible(true);
            assertEquals(digitInWords, method.invoke(WordUtils.class, number, Gender.MASCULINE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'121', 'одна'",
            "'122', 'две'",
            "'123', 'три'"})
    void testGetDigitInFeminineWords(String number, String digitInWords) {
        try {
            Method method = WordUtils.class.getDeclaredMethod("getDigitInWords", String.class, Gender.class);
            method.setAccessible(true);
            assertEquals(digitInWords, method.invoke(WordUtils.class, number, Gender.FEMININE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'121', 'одно'",
            "'122', 'два'",
            "'123', 'три'"})
    void testGetDigitInNeuterWords(String number, String digitInWords) {
        try {
            Method method = WordUtils.class.getDeclaredMethod("getDigitInWords", String.class, Gender.class);
            method.setAccessible(true);
            assertEquals(digitInWords, method.invoke(WordUtils.class, number, Gender.NEUTER));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'121', 'двадцать'",
            "'32', 'тридцать'"})
    void testGetDozenInWords(String number, String dozenInWords) {
        try {
            Method method = WordUtils.class.getDeclaredMethod("getDozenInWords", String.class);
            method.setAccessible(true);
            assertEquals(dozenInWords, method.invoke(WordUtils.class, number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'111', 'одиннадцать'",
            "'19', 'девятнадцать'"})
    void testGetDozenStartingWithOneInWords(String number, String dozenInWords) {
        try {
            Method method = WordUtils.class.getDeclaredMethod("getDozenStartingWithOneInWords", String.class);
            method.setAccessible(true);
            assertEquals(dozenInWords, method.invoke(WordUtils.class, number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"'111', 'сто'",
            "'401', 'четыреста'"})
    void testGetHundredInWords(String number, String hundredInWords) {
        try {
            Method method = WordUtils.class.getDeclaredMethod("getHundredInWords", String.class);
            method.setAccessible(true);
            assertEquals(hundredInWords, method.invoke(WordUtils.class, number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
