package ru.nn.homework.hw8.test;

import ru.nn.homework.hw8.test.unit.DiceTest;
import ru.nn.homework.hw8.test.unit.GameTest;

public class TestRunner {
    public static void main(String[] args) {
        new DiceTest().testRollReturnNumberBetweenOneAndSix();

        new GameTest().testForDraw();
        new GameTest().testForFirstPlayerWin();
        new GameTest().testForSecondPlayerWin();
    }
}
