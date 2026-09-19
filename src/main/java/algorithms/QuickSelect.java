package algorithms;

import java.util.Random;

public class QuickSelect {

    private static final Random random = new Random();

    public static int select(int[] array, int k) {
        return select(array, k, new Metrics());
    }

    public static int select(int[] array, int k, Metrics metrics) {

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

            long bounds =
                    QuickSort.partition(array, left, right, pivot, metrics);

            int less = (int) (bounds >> 32);
            int greater = (int) bounds;

            if (k < less) {
                right = less - 1;

            } else if (k > greater) {
                left = greater + 1;

            } else {
                return array[k];
            }
        }

        throw new IllegalStateException("Unexpected error");
    }
}