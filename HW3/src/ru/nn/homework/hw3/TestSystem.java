package ru.nn.homework.hw3;

import java.util.Scanner;

public class TestSystem {
    public static void main(String[] args) {
        // Переменные для хранения количества правильных и неправильных ответов
        int correctCount = 0;
        int wrongCount = 0;

        // Массив вопросов и вариантов ответов
        String[][] questionsAndAnswerOptions = {
                {"Кто написал роман \"Бесы\"?", "Л.Н. Толстой", "Ф.М. Достоевский", "Н.В. Гоголь", "И.С. Тургенев"},
                {"Как звали главного героя романа \"Преступление и наказание\"?", "Родион", "Фёдор", "Иван", "Алексей", "Николай"},
                {"Какое из перечисленных произведений написал не А.П. Чехов?", "Чайка", "Дядя Ваня", "Отцы и дети", "Палата № 6"}
        };

        // Массив правильных ответов
        int[] correctAnswers = {2, 1, 3};

        Scanner scanner = new Scanner(System.in);
        // Цикл по всем вопросам
        for (int i = 0; i < questionsAndAnswerOptions.length; i++) {
            System.out.println("Вопрос № " + (i + 1) + ". " + questionsAndAnswerOptions[i][0]);
            // Вывод вариантов ответов на экран
            for (int j = 1; j < questionsAndAnswerOptions[i].length; j++) {
                System.out.println(j + ". " + questionsAndAnswerOptions[i][j]);
            }

            // Считываем ответ и проверяем его корректность
            int userAnswer = 0;
            while (true) {
                System.out.print("Ваш ответ: ");
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt();
                } else {
                    scanner.next();
                }

                if (userAnswer < 1 || userAnswer > questionsAndAnswerOptions[i].length - 1) {
                    System.out.println("Некорректное значение. Введите число от 1 до " + (questionsAndAnswerOptions[i].length - 1));
                } else {
                    break;
                }
            }

            // Проверяем ответ и выводим результат,
            // а также увеличиваем счетчики правильных и неправильных ответов
            if (userAnswer == correctAnswers[i]) {
                correctCount++;
                System.out.println("Правильно!");
            } else {
                wrongCount++;
                System.out.println("Неправильно! Правильный ответ: " + (correctAnswers[i]));
            }
            System.out.println();
        }

        //Выводим общий результат
        System.out.println("Результат: правильно " + correctCount + ", неправильно " + wrongCount);
    }
}
