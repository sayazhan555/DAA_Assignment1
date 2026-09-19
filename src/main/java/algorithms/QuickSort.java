package algorithms;

public class QuickSort {

    // Old method — keeps existing tests working
    public static void sort(int[] array) {
        sort(array, new Metrics());
    }

    // New method with Metrics
    public static void sort(int[] array, Metrics metrics) {
        if (array == null || array.length < 2) {
            return;
        }

        quickSort(array, 0, array.length - 1, metrics);
    }

    private static void quickSort(int[] array, int left, int right,
                                  Metrics metrics) {

        metrics.enterRecursion();

        try {
            if (left >= right) {
                return;
            }

            int pivot = array[left + (right - left) / 2];

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

            quickSort(array, left, less - 1, metrics);
            quickSort(array, greater + 1, right, metrics);
        } finally {
            metrics.exitRecursion();
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}