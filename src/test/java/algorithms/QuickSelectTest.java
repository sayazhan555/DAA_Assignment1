package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class QuickSelectTest {

    @Test
    void testSelect() {
        int[] array = {7, 2, 9, 1, 5};

        assertEquals(1, QuickSelect.select(array.clone(), 0));
        assertEquals(2, QuickSelect.select(array.clone(), 1));
        assertEquals(5, QuickSelect.select(array.clone(), 2));
        assertEquals(7, QuickSelect.select(array.clone(), 3));
        assertEquals(9, QuickSelect.select(array.clone(), 4));
    }

    @Test
    void testDuplicates() {
        int[] array = {5, 3, 5, 1, 3, 5};

        assertEquals(1, QuickSelect.select(array.clone(), 0));
        assertEquals(3, QuickSelect.select(array.clone(), 1));
        assertEquals(3, QuickSelect.select(array.clone(), 2));
        assertEquals(5, QuickSelect.select(array.clone(), 3));
        assertEquals(5, QuickSelect.select(array.clone(), 4));
        assertEquals(5, QuickSelect.select(array.clone(), 5));
    }

    @Test
    void testInvalidK() {
        int[] array = {1, 2, 3};

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(array, -1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(array, 3)
        );
    }

    @Test
    void testEmptyArray() {
        int[] array = {};

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(array, 0)
        );
    }

    @Test
    void testRandomArrays() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(100) + 1;
            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000) - 500;
            }

            int[] expected = array.clone();
            Arrays.sort(expected);

            int k = random.nextInt(size);

            int result = QuickSelect.select(array.clone(), k);

            assertEquals(expected[k], result);
        }
    }

}