package ru.nn.homework.hw8.test.unit;

import ru.nn.homework.hw8.assertions.Assertions;
import ru.nn.homework.hw8.game.api.service.Dice;
import ru.nn.homework.hw8.game.api.service.GameWinnerPrinter;
import ru.nn.homework.hw8.game.domain.Game;
import ru.nn.homework.hw8.game.domain.Player;

public class GameTest {
    public void testForDraw () {
        Dice dice = () -> 3;

        String scenario = "Тест игры: ничья";
        try {
            StringBuffer expected = new StringBuffer("Ничья");
            StringBuffer actual = new StringBuffer();

            Player player1 = new Player("Вася");
            Player player2 = new Player("Игорь");
            GameWinnerPrinter winnerPrinter = new GameWinnerPrinterSpy(actual);
            Game game = new Game(dice, winnerPrinter);
            game.playGame(player1, player2);

            Assertions.assertEquals(expected.toString(), actual.toString());

            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public void testForFirstPlayerWin() {
            Dice dice = new Dice() {
                private boolean isFirstRollCall = true;

                @Override
                public int roll() {
                    if (isFirstRollCall) {
                        isFirstRollCall = false;
                        return 6;
                    }
                    return 3;
                }
            };

            String scenario = "Тест игры: выигрывает первый игрок";
            try {
                StringBuffer expected = new StringBuffer("Победитель: Вася");
                StringBuffer actual = new StringBuffer();

                Player player1 = new Player("Вася");
                Player player2 = new Player("Игорь");
                GameWinnerPrinter winnerPrinter = new GameWinnerPrinterSpy(actual);
                Game game = new Game(dice, winnerPrinter);
                game.playGame(player1, player2);

                Assertions.assertEquals(expected.toString(), actual.toString());

                System.out.printf("\"%s\" passed %n", scenario);
            } catch (Throwable e) {
                System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
            }
    }

    public void testForSecondPlayerWin() {
        Dice dice = new Dice() {
            private boolean isFirstRollCall = true;

            @Override
            public int roll() {
                if (isFirstRollCall) {
                    isFirstRollCall = false;
                    return 4;
                }
                return 5;
            }
        };

        String scenario = "Тест игры: выигрывает второй игрок";
        try {
            StringBuffer expected = new StringBuffer("Победитель: Игорь");
            StringBuffer actual = new StringBuffer();

            Player player1 = new Player("Вася");
            Player player2 = new Player("Игорь");
            GameWinnerPrinter winnerPrinter = new GameWinnerPrinterSpy(actual);
            Game game = new Game(dice, winnerPrinter);
            game.playGame(player1, player2);

            Assertions.assertEquals(expected.toString(), actual.toString());

            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }
}
