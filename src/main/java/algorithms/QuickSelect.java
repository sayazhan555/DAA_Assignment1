package algorithms;

import java.util.Random;

public class QuickSelect {

    private static final Random random = new Random();

    public static int select(int[] array, int k) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException("k is out of range");
        }

        int left = 0;
        int right = array.length - 1;


        while (left <= right) {
            int pivotIndex = left + random.nextInt(right - left + 1);
            int pivot = array[pivotIndex];

            int[] bounds = partition(array, left, right, pivot);

            if (k < bounds[0]) {
                right = bounds[0] - 1;
            } else if (k > bounds[1]) {
                left = bounds[1] + 1;
            } else {
                return array[k];
            }
        }

        throw new IllegalStateException("Unexpected error");
    }



    private static int[] partition(int[] array, int left, int right, int pivot) {
        int less = left;
        int current = left;
        int greater = right;

        while (current <= greater) {
            if (array[current] < pivot) {
                swap(array, less, current);
                less++;
                current++;
            } else if (array[current] > pivot) {
                swap(array, current, greater);
                greater--;
            } else {
                current++;
            }
        }

        return new int[]{less, greater};
    }


    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}