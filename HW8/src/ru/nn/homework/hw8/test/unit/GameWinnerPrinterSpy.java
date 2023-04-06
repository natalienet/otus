package ru.nn.homework.hw8.test.unit;

import ru.nn.homework.hw8.game.api.service.GameWinnerPrinter;
import ru.nn.homework.hw8.game.domain.Player;

public class GameWinnerPrinterSpy implements GameWinnerPrinter {
    private final StringBuffer actual;

    public GameWinnerPrinterSpy(StringBuffer actual) {
        this.actual = actual;
    }

    @Override
    public void printWinner(Player winner) {
        actual.append(String.format("Победитель: %s", winner.getName()));
    }
}