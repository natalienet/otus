package ru.nn.homework.hw7;


public class Question {
    private final String text;
    private final Option[] options;
    private final Integer correctOptionNumber;

    public Question(String text, Option[] options) throws CorrectOptionNotSet {
        if (options == null) {
            throw new IllegalArgumentException("options = null");
        }

        this.text = text;
        this.options = options;
        correctOptionNumber = findCorrectOptionNumber();
        if (correctOptionNumber == null)
            throw new CorrectOptionNotSet();
    }

    private Integer findCorrectOptionNumber() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isCorrect()) {
                return i + 1;
            }
        }

        return null;
    }

    public String getText() {
        return text;
    }

    public Option[] getOptions() {
        return options;
    }

    public Integer getCorrectOptionNumber() {
        return correctOptionNumber;
    }
}
