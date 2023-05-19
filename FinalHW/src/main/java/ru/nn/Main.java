package ru.nn;

import ru.nn.api.service.IOService;
import ru.nn.domain.MeasureUnit;
import ru.nn.domain.RubleUnit;
import ru.nn.service.IOStreamsService;
import ru.nn.service.NumberToNumberInWordsApplication;

public class Main {
    public static void main(String[] args) {
        IOService ioService = new IOStreamsService(System.out, System.in);
        MeasureUnit currency = new RubleUnit();
        NumberToNumberInWordsApplication application = new NumberToNumberInWordsApplication(ioService, currency);
        application.run();
    }
}
