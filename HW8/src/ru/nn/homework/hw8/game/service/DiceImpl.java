package ru.nn.homework.hw8.game.service;

import ru.nn.homework.hw8.game.api.service.Dice;

import java.util.Random;

public class DiceImpl implements Dice {
    @Override
    public int roll() {
        return new Random().nextInt();
    }
}
