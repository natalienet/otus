package ru.nn.homework.hw8.test.unit;

import ru.nn.homework.hw8.game.api.service.Dice;
import ru.nn.homework.hw8.assertions.*;

public class DiceTest {
    public void testRollReturnNumberBetweenOneAndSix() {
        Dice dice = () -> 3;

        String scenario = "Тест возврата числа от 1 до 6";
        try {
            int number = dice.roll();
            Assertions.assertEquals(3, number);
            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public void testRollReturnNumberLessThenOne() {
        Dice dice = () -> 0;

        String scenario = "Тест возврата числа меньше 1";

        try {
            Throwable actual = null;
            try {
                dice.roll();
            } catch (Throwable e) {
                actual = e;
            }

            if (actual == null) {
                throw new AssertionError("Given code does not throw any exception");
            } else {
                Assertions.assertEquals(Exception.class, actual.getClass());
            }

            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public void testRollReturnNumberBiggerSix() {
        Dice dice = () -> 10;

        String scenario = "Тест возврата числа больше 6";

        try {
            Throwable actual = null;
            try {
                dice.roll();
            } catch (Throwable e) {
                actual = e;
            }

            if (actual == null) {
                throw new AssertionError("Given code does not throw any exception");
            } else {
                Assertions.assertEquals(Exception.class, actual.getClass());
            }

            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }
}
