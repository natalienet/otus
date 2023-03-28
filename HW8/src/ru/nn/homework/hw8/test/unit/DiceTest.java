package ru.nn.homework.hw8.test.unit;

import ru.nn.homework.hw8.game.service.DiceImpl;

public class DiceTest {
    private final DiceImpl dice = new DiceImpl();

    public void testRollReturnNumberBetweenOneAndSix() {
        String scenario = "Тест возврата числа от 1 до 6";
        try {
            int number = dice.roll();
            if (number < 1 || number > 6) {
                throw new AssertionError(String.format("Expected [1-6] = %d", number));
            }

            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }
}
