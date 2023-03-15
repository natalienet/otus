package ru.nn.homework.hw7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
    private String name;
    private Question[] questions;

    public Test() {}

    public Test(String name, Question[] questions) {
        if (questions == null) {
            throw new IllegalArgumentException("questions = null");
        }

        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void upload(final String fileName) throws IOException {
        try (FileInputStream file = new FileInputStream(fileName)) {
            file.read();
        }
    }
}
