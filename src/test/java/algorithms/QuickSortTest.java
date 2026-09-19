package algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {


    @Test
    void testRandomArrays() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(100);
            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000) - 500;
            }

            int[] expected = array.clone();
            Arrays.sort(expected);

            QuickSort.sort(array);

            assertArrayEquals(expected, array);
        }
    }


    @Test
    void testEdgeCases() {
        int[] empty = {};
        QuickSort.sort(empty);
        assertArrayEquals(new int[]{}, empty);

        int[] oneElement = {5};
        QuickSort.sort(oneElement);
        assertArrayEquals(new int[]{5}, oneElement);

        int[] equal = {7, 7, 7, 7, 7};
        QuickSort.sort(equal);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, equal);

        int[] sorted = {1, 2, 3, 4, 5};
        QuickSort.sort(sorted);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
    }
}