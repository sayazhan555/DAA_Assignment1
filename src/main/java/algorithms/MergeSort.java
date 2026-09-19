package algorithms;

public class MergeSort {

    private static final int CUTOFF = 15;

    // Old method — keeps existing tests working
    public static void sort(int[] array) {
        sort(array, new Metrics());
    }

    // New method with Metrics
    public static void sort(int[] array, Metrics metrics) {
        if (array == null || array.length < 2) {
            return;
        }

        int[] buffer = new int[array.length];

        mergeSort(array, buffer, 0, array.length - 1, metrics);
    }

    private static void mergeSort(int[] array, int[] buffer,
                                  int left, int right, Metrics metrics) {

        if (left >= right) {
            return;
        }

        metrics.enterRecursion();

        if (right - left + 1 <= CUTOFF) {
            insertionSort(array, left, right, metrics);
            metrics.exitRecursion();
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(array, buffer, left, middle, metrics);
        mergeSort(array, buffer, middle + 1, right, metrics);

        merge(array, buffer, left, middle, right, metrics);

        metrics.exitRecursion();
    }

    private static void insertionSort(int[] array, int left,
                                      int right, Metrics metrics) {

        for (int i = left + 1; i <= right; i++) {

            int current = array[i];
            int j = i - 1;

            while (j >= left) {

                // Count comparison between array[j] and current
                metrics.addComparison();

                if (array[j] > current) {
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }

            array[j + 1] = current;
        }
    }

    private static void merge(int[] array, int[] buffer,
                              int left, int middle, int right,
                              Metrics metrics) {

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

            } else {

                // Count comparison between elements
                metrics.addComparison();

                if (buffer[i] <= buffer[j]) {
                    array[k] = buffer[i];
                    i++;
                } else {
                    array[k] = buffer[j];
                    j++;
                }
            }
        }
    }
}