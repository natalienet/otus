package ru.nn.spy;

import ru.nn.api.service.IOService;

import java.util.List;

public class IOServiceSpy implements IOService {
    private final List<String> actualFlow;
    private int numberOfInputCall;

    public IOServiceSpy(List<String> actualFlow) {
        this.actualFlow = actualFlow;
        numberOfInputCall = 0;
    }

    @Override
    public void outputString(String message) {
        actualFlow.add(message);
    }

    @Override
    public String readString() {
        String inputString = switch (numberOfInputCall) {
            case 0 -> "1345678900";
            case 1 -> "-123";
            case 2 -> "abcd";
            case 3 -> "2345601";
            default -> "";
        };
        numberOfInputCall++;
        actualFlow.add(inputString);
        return inputString;
    }
}
