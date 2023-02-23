package ru.nn.homework.hw6;

import java.util.Objects;

public class Account {
    private final int number;

    public Account(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Счет № " + number;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Account otherAccount = (Account) other;
        return Objects.equals(number, otherAccount.number);
    }
}
