package ru.nn.homework.hw7;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestSystem {
    private int correctCount = 0;
    private int wrongCount = 0;
    private int userAnswer;
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws CorrectOptionNotSet {
        TestSystem testSystem = new TestSystem();
        Test test1 = uploadTest();
        try {
            testSystem.start(test1);
        } catch (Exception e) {
            System.exit(1);
        }

        String fileName = "test.txt";
        try {
            Test test2 = uploadTest(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл " + fileName);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла " + fileName);
            e.printStackTrace();
        }
    }

    public void start(Test test) throws Exception {
        int questionNumber = 0;

        try {
            printTestName(test.getName());
            for (Question question : test.getQuestions()) {
                questionNumber++;
                int correctOptionNumber = question.getCorrectOptionNumber();
                String questionText = question.getText();
                printQuestion(questionNumber, questionText);

                Option[] options = question.getOptions();
                printOptions(options);

                readAndCheckValidityOfAnswer(options.length);
                checkAnswerAndIncreaseCounter(options, correctOptionNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        printResult();
        resetResult();
    }

    private void printTestName(String testName) {
        System.out.println(testName);
    }

    private void printQuestion(int number, String text) {
        System.out.println("Вопрос № " + number + ". " + text);
    }

    private void printOptions(Option[] options) {
        for (int i = 0; i < options.length; i++) {
            String optionText = options[i].getText();
            System.out.println((i + 1) + ". " + optionText);
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

    private void checkAnswerAndIncreaseCounter(Option[] options, int correctOptionNumber) {
        if (options[userAnswer - 1].isCorrect()) {
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

    public static Test uploadTest() throws CorrectOptionNotSet {
        Question[] questions = new Question[3];

        int questionNum = 0;
        Option[] options1 = new Option[4];
        options1[0] = new Option("Л.Н. Толстой", false);
        options1[1] = new Option("Ф.М. Достоевский", true);
        options1[2] = new Option("Н.В. Гоголь", false);
        options1[3] = new Option("И.С. Тургенев", false);
        try {
            questions[questionNum] = new Question("Кто написал роман \"Бесы\"?", options1);
            questionNum++;
        } catch (Exception e) {
            System.out.println("Вопрос не загружен");
            e.printStackTrace();
        }

        Option[] options2 = new Option[5];
        options2[0] = new Option("Родион", true);
        options2[1] = new Option("Фёдор", false);
        options2[2] = new Option("Иван", false);
        options2[3] = new Option("Алексей", false);
        options2[4] = new Option("Алексей", false);
        try {
            questions[questionNum] = new Question("Как звали главного героя романа \"Преступление и наказание\"?", options2);
            questionNum++;
        } catch (Exception e) {
            System.out.println("Вопрос не загружен");
            e.printStackTrace();
        }

        Option[] options3 = new Option[4];
        options3[0] = new Option("Чайка", false);
        options3[1] = new Option("Дядя Ваня", false);
        options3[2] = new Option("Отцы и дети", true);
        options3[3] = new Option("Палата № 6", false);
        try {
            questions[questionNum] = new Question("Какое из перечисленных произведений написал не А.П. Чехов?", options3);
            questionNum++;
        } catch (Exception e) {
            System.out.println("Вопрос не загружен");
            e.printStackTrace();
        }

        Option[] options4 = new Option[4];
        options4[0] = new Option("И.С. Тургенев", false);
        options4[1] = new Option("Н.С. Лесков", true);
        options4[2] = new Option("И.А. Гончаров", false);
        try {
            questions[questionNum] = new Question("Кто автор повести \"Леди Макбет Мценского уезда\"?", options4);
            questionNum++;
        } catch (Exception e) {
            System.out.println("Вопрос не загружен");
            e.printStackTrace();
        }

        Test test = null;
        try {
            test = new Test("ТЕСТ ПО ЛИТЕРАТУРЕ ДЛЯ УЧАЩИХСЯ 9-Х КЛАССОВ", questions);
        } catch (IllegalArgumentException e) {
            System.out.println("Не задан список вопросов");
            e.printStackTrace();
        }

        return test;
    }

    public static Test uploadTest(String fileName) throws IOException {
        Test test = new Test();
        test.upload(fileName);
        return test;
    }
}
