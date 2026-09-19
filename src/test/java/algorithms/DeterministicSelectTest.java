package algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testRandomArrays() {

        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {

            int n = 1 + random.nextInt(100);
            int[] array = new int[n];

            for (int i = 0; i < n; i++) {
                array[i] = random.nextInt(1000);
            }

            int[] sorted = array.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(n);

            int result =
                    DeterministicSelect.select(array.clone(), k);

            assertEquals(sorted[k], result);
        }
    }

    @Test
    void testSortedArray() {

        int[] array = new int[1000];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        int result =
                DeterministicSelect.select(array.clone(), 500);

        assertEquals(500, result);
    }

    @Test
    void testDuplicates() {

        int[] array = {
                5, 5, 5, 1, 1,
                9, 9, 3, 3, 3
        };

        int[] sorted = array.clone();
        Arrays.sort(sorted);

        for (int k = 0; k < array.length; k++) {

            int result =
                    DeterministicSelect.select(array.clone(), k);

            assertEquals(sorted[k], result);
        }
    }

    @Test
    void testInvalidInput() {

        assertThrows(
                IllegalArgumentException.class,
                () -> DeterministicSelect.select(new int[0], 0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> DeterministicSelect.select(
                        new int[]{1, 2, 3}, -1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> DeterministicSelect.select(
                        new int[]{1, 2, 3}, 3)
        );
    }
}