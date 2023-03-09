package ru.nn.homework.hw4;

public class Option {
    private final Question question;
    private final int number;
    private final String text;

    public Option(int number, String text, Question question) {
        if (question == null) {
            throw new IllegalArgumentException("question");
        }

        this.number = number;
        this.text = text;
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public int getNumber() {
        return number;
    }
}
