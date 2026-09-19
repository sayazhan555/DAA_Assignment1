package algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    void testRandomArrays() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(50) + 1;
            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(100) - 50;
            }

            int[] expected = array.clone();
            Arrays.sort(expected);

            MergeSort.sort(array);

            assertArrayEquals(expected, array);
        }
    }

    @Test
    void testEdgeCases() {
        int[] empty = {};
        MergeSort.sort(empty);
        assertArrayEquals(new int[]{}, empty);

        int[] oneElement = {5};
        MergeSort.sort(oneElement);
        assertArrayEquals(new int[]{5}, oneElement);

        int[] equal = {7, 7, 7, 7, 7};
        MergeSort.sort(equal);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, equal);

        int[] sorted = {1, 2, 3, 4, 5};
        MergeSort.sort(sorted);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
    }
}