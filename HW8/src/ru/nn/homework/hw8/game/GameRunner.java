package ru.nn.homework.hw8.game;

import ru.nn.homework.hw8.game.api.service.Dice;
import ru.nn.homework.hw8.game.api.service.GameWinnerPrinter;
import ru.nn.homework.hw8.game.domain.Game;
import ru.nn.homework.hw8.game.domain.Player;
import ru.nn.homework.hw8.game.service.DiceImpl;
import ru.nn.homework.hw8.game.service.GameWinnerConsolePrinter;

public class GameRunner {

    public static void main(String[] args) {
        Dice dice = new DiceImpl();
        GameWinnerPrinter winnerPrinter = new GameWinnerConsolePrinter();
        Game game = new Game(dice, winnerPrinter);
        game.playGame(new Player("Вася"), new Player("Игорь"));
    }
}
