package ru.nn.service;

import ru.nn.api.service.IOService;
import ru.nn.domain.MeasureUnit;

public class NumberToNumberInWordsApplication {
    private final IOService ioService;
    private final MeasureUnit currency;
    private static final int MAX_NUMBER = 999_999_999;

    public NumberToNumberInWordsApplication(IOService ioService, MeasureUnit currency) {
        this.ioService = ioService;
        this.currency = currency;
    }

    public void run(){
        String numberAsString;
        int number;

        while (true) {
            numberAsString = showPromptAndReadNumber();
            try {
                number = Integer.parseInt(numberAsString.trim());
                if (number > MAX_NUMBER) {
                    ioService.outputString("Введено число больше максимального");
                } else if (number < 0) {
                    ioService.outputString("Введено отрицательное число");
                } else {
                    break;
                }
            }
            catch (NumberFormatException e) {
                ioService.outputString("Введено не число");
            }
        }

        try {
            String numberInWords = NumberToNumberInWordsConverter.convertNumber(number, currency);
            ioService.outputString(numberInWords);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String showPromptAndReadNumber() {
        ioService.outputString("Введите целое число не больше " + MAX_NUMBER + ":");
        return ioService.readString();
    }
}
