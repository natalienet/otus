package ru.nn.homework.hw6;

import java.util.Objects;

public class Client {
    private final String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ФИО: " + name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Client otherClient = (Client) other;
        return Objects.equals(name, otherClient.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
