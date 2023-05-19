package ru.nn.service;

import ru.nn.domain.Gender;
import ru.nn.domain.MeasureUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NumberToNumberInWordsConverter {
    private static final int DIGIT_COUNT_IN_GROUP = 3;

    public static String convertNumber(int number, MeasureUnit measureUnit) throws Exception {
        String numberAsString = String.valueOf(number);
        if (number == 0) {
            return getZeroInWords(measureUnit);
        }

        List<String> digitGroups = splitNumberStringIntoDigitGroupStrings(numberAsString, DIGIT_COUNT_IN_GROUP);
        List<String> numberInWords = new ArrayList<>();
        switch (digitGroups.size()) {
            case 3:
                numberInWords.add(getMillionthPartInWords(digitGroups.get(2)));
            case 2:
                numberInWords.add(getThousandthPartInWords(digitGroups.get(1)));
            case 1:
                numberInWords.add(getHundredthPartInWords(digitGroups.get(0), measureUnit));
                break;
            default:
                throw new Exception("Entered number greater than the maximum");
        }

        return String.join(" ", numberInWords);
    }

    private static List<String> splitNumberStringIntoDigitGroupStrings(String numberAsString, int digitCount) {
        List<String> threeNumbers = new ArrayList<>();
        int length = numberAsString.length();
        for (int i = length, k = 0; k < length; i -= digitCount, k += digitCount) {
            threeNumbers.add(numberAsString.substring(Math.max(0, i - digitCount), i));
        }
        return threeNumbers;
    }

    private static String getHundredthPartInWordsWithoutMeasureUnit(String hundredPart, Gender gender) {
        return WordUtils.getNumberInWords(hundredPart, gender);
    }

    private static String getHundredthPartInWords(String hundredPart, MeasureUnit measureUnit) {
        String hundredInWords = getHundredthPartInWordsWithoutMeasureUnit(hundredPart, measureUnit.getGender());
        return hundredInWords.isEmpty() ? WordUtils.getMeasureUnitStringForNumber(hundredPart, measureUnit) :
                hundredInWords + " " + WordUtils.getMeasureUnitStringForNumber(hundredPart, measureUnit);
    }

    private static String getZeroInWords(MeasureUnit measureUnit) {
        return String.format("ноль %s", WordUtils.getMeasureUnitStringForNumber("0", measureUnit));
    }

    private static String getThousandthPartInWords(String thousandPart) {
        String thousandInWords = WordUtils.getNumberInWords(thousandPart, WordUtils.thousandUnit.getGender());
        return thousandInWords.isEmpty() ? "" : thousandInWords + " " +
                WordUtils.getMeasureUnitStringForNumber(thousandPart, WordUtils.thousandUnit);
    }

    private static String getMillionthPartInWords(String millionPart) {
        String millionInWords = WordUtils.getNumberInWords(millionPart, WordUtils.millionUnit.getGender());
        return millionInWords.isEmpty() ? "" : millionInWords + " " +
                WordUtils.getMeasureUnitStringForNumber(millionPart, WordUtils.millionUnit);
    }
}

