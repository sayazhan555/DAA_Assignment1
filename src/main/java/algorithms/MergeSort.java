package algorithms;

public class MergeSort {

    private static final int CUTOFF = 15;

    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int[] buffer = new int[array.length];
        mergeSort(array, buffer, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] buffer, int left, int right) {
        if (left >= right) {
            return;
        }

        if (right - left + 1 <= CUTOFF) {
            insertionSort(array, left, right);
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(array, buffer, left, middle);
        mergeSort(array, buffer, middle + 1, right);

        merge(array, buffer, left, middle, right);
    }

    private static void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int current = array[i];
            int j = i - 1;

            while (j >= left && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = current;
        }
    }

    private static void merge(int[] array, int[] buffer,
                              int left, int middle, int right) {

        for (int i = left; i <= right; i++) {
            buffer[i] = array[i];
        }

        int i = left;
        int j = middle + 1;

        for (int k = left; k <= right; k++) {

            if (i > middle) {
                array[k] = buffer[j];
                j++;
            } else if (j > right) {
                array[k] = buffer[i];
                i++;
            } else if (buffer[i] <= buffer[j]) {
                array[k] = buffer[i];
                i++;
            } else {
                array[k] = buffer[j];
                j++;
            }
        }
    }


}
