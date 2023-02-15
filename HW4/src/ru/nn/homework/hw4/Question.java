package ru.nn.homework.hw4;


public class Question {

    /* Не стала делать поле "Номер вопроса", т.к. один и тот же вопрос может
       входить в состав разных тестов, соответственно, его номер в каждом тесте
       может быть разным.
    */

    private final String text;
    private final Option[] options;
    private int countOfAddedOptions = 0;
    private int correctOptionNumber;

    public Question(String text, int countOfOptions) {
        this.text = text;
        options = new Option[countOfOptions];
    }

    public String getText() {
        return text;
    }

    public Option[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctOptionNumber;
    }

    public void setCorrectOptionNumber(int correctOptionNumber) {
        this.correctOptionNumber = correctOptionNumber;
    }

    public void addOption(Option option) {
        if (option == null) {
            throw new IllegalArgumentException("option");
        }

        if (countOfAddedOptions >= options.length) {
            System.out.println("Внимание! Уже добавлено заданное количество вариантов ответов.");
            return;
        }

        options[countOfAddedOptions] = option;
        countOfAddedOptions++;
    }

    public int getCountOfOptions() {
        return options.length;
    }
}
