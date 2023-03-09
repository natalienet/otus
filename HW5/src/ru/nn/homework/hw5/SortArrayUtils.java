package ru.nn.homework.hw5;

import java.util.Arrays;
import java.util.Random;

public class SortArrayUtils {
    public static void main(String[] args) {
        final int ARRAY_LENGTH = 10_000;
        int[] array1 = getRandomArray(ARRAY_LENGTH);
        int[] array2 = Arrays.copyOf(array1, ARRAY_LENGTH);
        int[] array3 = Arrays.copyOf(array1, ARRAY_LENGTH);

        long startTime = System.currentTimeMillis();
        sortByChoice(array1);
        long finishTime = System.currentTimeMillis();
        long duration = finishTime - startTime;
        System.out.println("Sorting by choice time = " + duration);

        startTime = System.currentTimeMillis();
        sortByBubble(array2);
        finishTime = System.currentTimeMillis();
        duration = finishTime - startTime;
        System.out.println("Sorting by bubble time = " + duration);

        startTime = System.currentTimeMillis();
        Arrays.sort(array3);
        finishTime = System.currentTimeMillis();
        duration = finishTime - startTime;
        System.out.println("Standard sorting time = " + duration);
    }

    private static int[] getRandomArray(int length) {
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(100);
        }

        return array;
    }

    private static void sortByChoice(int[] array) {
        int minIndex, minNumber;
        for (int i = 0; i < array.length - 1; i++) {
            minIndex = i;
            minNumber = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (minNumber > array[j]) {
                    minNumber = array[j];
                    minIndex = j;
                }
            }
            array[minIndex] = array[i];
            array[i] = minNumber;

        }
    }

    private static void sortByBubble(int[] array) {
        int temp;
        for (int k = array.length - 1; k > 0; k--) {
            for (int i = 0; i < k; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
    }
}


