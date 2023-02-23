package ru.nn.homework.hw5;

import java.util.Arrays;

public class SortArrayUtils {
    public static void main(String[] args) {
        int[] array = {33, 2, 6, -3, 45, 12, -66};
        sortByChoice(array);
        //sortByBubble(array);
        System.out.println("After: " + Arrays.toString(array));
    }

    public static void sortByChoice(int[] array) {
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

    public static void sortByBubble(int[] array) {
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

