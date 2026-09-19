package algorithms;

public class DeterministicSelect {

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

        return select(array, 0, array.length - 1, k, metrics);
    }

    private static int select(
            int[] array,
            int left,
            int right,
            int k,
            Metrics metrics) {

        if (left == right) {
            return array[left];
        }

        int pivot = medianOfMedians(array, left, right, metrics);

        int[] bounds = partition(
                array, left, right, pivot, metrics);

        int less = bounds[0];
        int greater = bounds[1];

        if (k < less) {
            return select(
                    array, left, less - 1, k, metrics);

        } else if (k > greater) {
            return select(
                    array, greater + 1, right, k, metrics);

        } else {
            return array[k];
        }
    }

    private static int medianOfMedians(
            int[] array,
            int left,
            int right,
            Metrics metrics) {

        int n = right - left + 1;

        if (n <= 5) {
            insertionSort(array, left, right, metrics);
            return array[left + n / 2];
        }

        int groups = (n + 4) / 5;
        int[] medians = new int[groups];

        int index = 0;

        for (int start = left; start <= right; start += 5) {

            int end = Math.min(start + 4, right);

            insertionSort(array, start, end, metrics);

            int middle = start + (end - start) / 2;

            medians[index] = array[middle];
            index++;
        }

        return medianOfMedians(
                medians,
                0,
                medians.length - 1,
                metrics);
    }

    private static int[] partition(
            int[] array,
            int left,
            int right,
            int pivot,
            Metrics metrics) {

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

        return new int[]{less, greater};
    }

    private static void insertionSort(
            int[] array,
            int left,
            int right,
            Metrics metrics) {

        for (int i = left + 1; i <= right; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= left) {

                metrics.addComparison();

                if (array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }

            array[j + 1] = key;
        }
    }

    private static void swap(
            int[] array,
            int i,
            int j) {

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}