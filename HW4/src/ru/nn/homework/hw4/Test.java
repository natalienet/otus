package ru.nn.homework.hw4;

public class Test {
    private final String name;
    private final Question[] questions;
    private int countOfAddedQuestions = 0;

    public Test(String name, int countOfQuestions) {
        this.name = name;
        this.questions = new Question[countOfQuestions];
    }

    public String getName() {
        return name;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("question");
        }

        if (countOfAddedQuestions >= questions.length) {
            System.out.println("Внимание! Уже добавлено заданное количество вопросов.");
            return;
        }
        questions[countOfAddedQuestions] = question;
        countOfAddedQuestions++;
    }
}
