package ru.nn.homework.hw8.game.service;

import ru.nn.homework.hw8.game.api.service.GameWinnerPrinter;
import ru.nn.homework.hw8.game.domain.Player;

public class GameWinnerConsolePrinter implements GameWinnerPrinter {
    @Override
    public void printWinner(Player winner) {
        System.out.printf("Победитель: %s%n", winner.getName());
    }
}
