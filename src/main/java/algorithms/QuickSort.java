package algorithms;

import java.util.Random;

public class QuickSort {

    private static final Random random = new Random();

    public static void sort(int[] array) {
        sort(array, new Metrics());
    }

    public static void sort(int[] array, Metrics metrics) {
        if (array == null || array.length < 2) {
            return;
        }

        quickSort(array, 0, array.length - 1, metrics);
    }

    private static void quickSort(int[] array, int left, int right,
                                  Metrics metrics) {

        while (left < right) {

            metrics.enterRecursion();

            int pivotIndex = left + random.nextInt(right - left + 1);
            int pivot = array[pivotIndex];

            long bounds = partition(array, left, right, pivot, metrics);

            int less = (int) (bounds >> 32);
            int greater = (int) bounds;

            int leftSize = less - left;
            int rightSize = right - greater;

            if (leftSize < rightSize) {

                quickSort(array, left, less - 1, metrics);
                metrics.exitRecursion();

                left = greater + 1;

            } else {

                quickSort(array, greater + 1, right, metrics);
                metrics.exitRecursion();

                right = less - 1;
            }
        }
    }

    // 3-way partition: elements smaller, equal and greater than pivot
    static long partition(int[] array, int left, int right,
                          int pivot, Metrics metrics) {

        int less = left;
        int current = left;
        int greater = right;

        while (current <= greater) {

            metrics.addComparison();

            if (array[current] < pivot) {
                swap(array, less, current);
                less++;
                current++;

            } else {

                metrics.addComparison();

                if (array[current] > pivot) {
                    swap(array, current, greater);
                    greater--;

                } else {
                    current++;
                }
            }
        }

        return ((long) less << 32) | (greater & 0xffffffffL);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}