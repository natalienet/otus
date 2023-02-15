package ru.nn.homework.hw4;

import java.util.Scanner;

public class TestSystem {
    private Test currentTest;
    private int correctCount = 0;
    private int wrongCount = 0;
    private int userAnswer;
    private final Scanner scanner = new Scanner(System.in);

    public void setCurrentTest(Test currentTest) {
        this.currentTest = currentTest;
    }

    public void start() {
        printTestName();

        int questionNumber = 1;
        for (Question question : currentTest.getQuestions()) {
            String questionText = question.getText();
            printQuestion(questionNumber, questionText);
            questionNumber++;

            Option[] options = question.getOptions();
            printOptions(options);

            int countOfOptions = question.getCountOfOptions();
            readAndCheckValidityOfAnswer(countOfOptions);

            int correctAnswer = question.getCorrectAnswer();
            checkAnswerAndIncreaseCounter(correctAnswer);
        }

        printResult();
        resetResult();
    }

    private void printTestName() {
        System.out.println(currentTest.getName());
    }

    private void printQuestion(int number, String text) {
        System.out.println("Вопрос № " + number + ". " + text);
    }

    private void printOptions(Option[] options) {
        if (options == null) {
            throw new IllegalArgumentException("options");
        }

        for (Option option : options) {
            String optionText = option.getText();
            System.out.println(option.getNumber() + ". " + optionText);
        }
    }

    private void readAndCheckValidityOfAnswer(int countOfOptions) {
        userAnswer = 0;
        while (true) {
            System.out.print("Ваш ответ: ");
            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
            } else {
                scanner.next();
            }

            if (userAnswer < 1 || userAnswer > countOfOptions) {
                System.out.println("Некорректное значение. Введите число от 1 до " + countOfOptions + ".");
            } else {
                break;
            }
        }
    }

    private void checkAnswerAndIncreaseCounter(int correctOptionNumber) {
        if (userAnswer == correctOptionNumber) {
            correctCount++;
            System.out.println("Правильно!");
        } else {
            wrongCount++;
            System.out.println("Неправильно! Правильный ответ: " + correctOptionNumber + ".");
        }
        System.out.println();
    }

    private void printResult() {
        System.out.println("Результат: правильных ответов " + correctCount + ", неправильных ответов " +
                wrongCount + ".");
    }

    private void resetResult() {
        correctCount = 0;
        wrongCount = 0;
    }

    public static void main(String[] args) {
        TestSystem testSystem = new TestSystem();
        Test test = new Test("ТЕСТ ПО ЛИТЕРАТУРЕ ДЛЯ УЧАЩИХСЯ 9-Х КЛАССОВ", 3);

        Question question_1 = new Question("Кто написал роман \"Бесы\"?", 4);
        Option option_1_1 = new Option(1, "Л.Н. Толстой", question_1);
        Option option_1_2 = new Option(2, "Ф.М. Достоевский", question_1);
        Option option_1_3 = new Option(3, "Н.В. Гоголь", question_1);
        Option option_1_4 = new Option(4, "И.С. Тургенев", question_1);
        question_1.addOption(option_1_1);
        question_1.addOption(option_1_2);
        question_1.addOption(option_1_3);
        question_1.addOption(option_1_4);
        question_1.setCorrectOptionNumber(2);

        Question question_2 = new Question("Как звали главного героя романа \"Преступление и наказание\"?", 5);
        Option option_2_1 = new Option(1, "Родион", question_2);
        Option option_2_2 = new Option(2, "Фёдор", question_2);
        Option option_2_3 = new Option(3, "Иван", question_2);
        Option option_2_4 = new Option(4, "Алексей", question_2);
        Option option_2_5 = new Option(5, "Алексей", question_2);
        question_2.addOption(option_2_1);
        question_2.addOption(option_2_2);
        question_2.addOption(option_2_3);
        question_2.addOption(option_2_4);
        question_2.addOption(option_2_5);
        question_2.setCorrectOptionNumber(1);

        Question question_3 = new Question("Какое из перечисленных произведений написал не А.П. Чехов?", 4);
        Option option_3_1 = new Option(1, "Чайка", question_3);
        Option option_3_2 = new Option(2, "Дядя Ваня", question_3);
        Option option_3_3 = new Option(3, "Отцы и дети", question_3);
        Option option_3_4 = new Option(4, "Палата № 6", question_3);
        question_3.addOption(option_3_1);
        question_3.addOption(option_3_2);
        question_3.addOption(option_3_3);
        question_3.addOption(option_3_4);
        question_3.setCorrectOptionNumber(3);

        test.addQuestion(question_1);
        test.addQuestion(question_2);
        test.addQuestion(question_3);

        testSystem.setCurrentTest(test);
        testSystem.start();
    }

}
