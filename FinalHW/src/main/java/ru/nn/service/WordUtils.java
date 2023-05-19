package ru.nn.service;

import ru.nn.domain.Gender;
import ru.nn.domain.MeasureUnit;
import ru.nn.domain.MillionUnit;
import ru.nn.domain.ThousandUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WordUtils {
    public static final MillionUnit millionUnit = new MillionUnit();
    public static final ThousandUnit thousandUnit = new ThousandUnit();

    public static final Map<Integer, List<String>> digitInGenderWords = Map.of(
            0, List.of("", "", ""),
            1, List.of("один", "одна", "одно"),
            2, List.of("два", "две", "два"),
            3, List.of("три", "три", "три"),
            4, List.of("четыре", "четыре", "четыре"),
            5, List.of("пять", "пять", "пять"),
            6, List.of("шесть", "шесть", "шесть"),
            7, List.of("семь", "семь", "семь"),
            8, List.of("восемь", "восемь", "восемь"),
            9, List.of("девять", "девять", "девять")
    );

    public static final Map<Integer, String> numbersFrom10To19InWords = Map.of(
            10, "десять",
            11, "одиннадцать",
            12, "двенадцать",
            13, "тринадцать",
            14, "четырнадцать",
            15, "пятнадцать",
            16, "шестнадцать",
            17, "семнадцать",
            18, "восемнадцать",
            19, "девятнадцать"
    );

    public static final Map<Integer, String> dozensInWords = Map.of(
            0, "",
            2, "двадцать",
            3, "тридцать",
            4, "сорок",
            5, "пятьдесят",
            6, "шестьдесят",
            7, "семьдесят",
            8, "восемьдесят",
            9, "девяносто"
    );

    public static final Map<Integer, String> hundredsInWords = Map.of(
            1, "сто",
            2, "двести",
            3, "триста",
            4, "четыреста",
            5, "пятьсот",
            6, "шестьсот",
            7, "семьсот",
            8, "восемьсот",
            9, "девятьсот"
    );

    public static String getMeasureUnitStringForNumber(String numberAsString, MeasureUnit measureUnit) {
        if (measureUnit == null) {
            throw new NullPointerException("measureUnit should not be null");
        }

        if (numberAsString.isEmpty()) {
            return "";
        }

        int numberStringLength = numberAsString.length();
        int lastDigit = Integer.parseInt(String.valueOf(numberAsString.charAt(numberStringLength - 1)));
        int penultimateDigit = 0;

        if (numberStringLength > 1) {
            penultimateDigit = Integer.parseInt(String.valueOf(numberAsString.charAt(numberStringLength - 2)));
        }

        if (lastDigit == 0 ||
                (numberStringLength > 1 && penultimateDigit == 1) ||
                (lastDigit >= 5 && lastDigit <= 9)) {
            return measureUnit.getPlural();
        } else if (lastDigit == 1 && (numberStringLength == 1 ||
                (numberStringLength > 1 && penultimateDigit != 1))) {
            return measureUnit.getSingular();
        }
        return measureUnit.getSingularAndGenitive();
    }

    public static String getNumberInWords(String numberAsString, Gender gender) {
        List<String> numberInWords = new ArrayList<>();
        numberAsString = trimStartingZeros(numberAsString);
        int numberAsStringLength = numberAsString.length();

        switch (numberAsStringLength) {
            case 3:
                numberInWords.add(getHundredInWords(numberAsString));
            case 2:
                if (checkDigitAtPositionEqualsOne(numberAsString, numberAsStringLength - 2)) {
                    numberInWords.add(getDozenStartingWithOneInWords(numberAsString));
                    break;
                }
                numberInWords.add(getDozenInWords(numberAsString));
            case 1:
                numberInWords.add(getDigitInWords(numberAsString, gender));
        }

        Predicate<String> empty = str -> str.isEmpty();
        numberInWords.removeIf(str -> empty.test(str));
        return String.join(" ", numberInWords);
    }

    private static String trimStartingZeros(String numberAsString) {
        while (numberAsString.startsWith("0")) {
            numberAsString = numberAsString.substring(1);
        }

        return numberAsString;
    }

    private static String getHundredInWords(String numberAsString) {
        int firstDigit = getDigitFromCharAtPosition(numberAsString, 0);
        return hundredsInWords.get(firstDigit);
    }

    private static String getDozenStartingWithOneInWords(String numberAsString) {
        int dozen = getNumberFromSubstringAtPosition(numberAsString, numberAsString.length() - 2);
        return numbersFrom10To19InWords.get(dozen);
    }

    private static String getDozenInWords(String numberAsString) {
        int secondDigit = getDigitFromCharAtPosition(numberAsString, numberAsString.length() - 2);
        return dozensInWords.get(secondDigit);
    }

    private static String getDigitInWords(String numberAsString, Gender gender) {
        int thirdDigit = getDigitFromCharAtPosition(numberAsString, numberAsString.length() - 1);
        String digitInWords;
        if (gender == Gender.MASCULINE) {
            digitInWords = digitInGenderWords.get(thirdDigit).get(0);
        } else if (gender == Gender.FEMININE) {
            digitInWords = digitInGenderWords.get(thirdDigit).get(1);
        } else {
            digitInWords = digitInGenderWords.get(thirdDigit).get(2);
        }
        return digitInWords;
    }

    private static int getDigitFromCharAtPosition(String numberAsString, int position) {
        return Character.getNumericValue(numberAsString.charAt(position));
    }

    private static int getNumberFromSubstringAtPosition(String numberAsString, int position) {
        return Integer.parseInt(numberAsString.substring(position));
    }

    private static boolean checkDigitAtPositionEqualsOne(String numberAsString, int position) {
        int digit = getDigitFromCharAtPosition(numberAsString, position);
        return digit == 1;
    }
}
