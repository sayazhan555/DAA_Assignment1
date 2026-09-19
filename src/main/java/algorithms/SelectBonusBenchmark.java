package algorithms;

import java.util.Arrays;
import java.util.Random;

public class SelectBonusBenchmark {

    public static void main(String[] args) {

        int[] sizes = {1000, 10000, 100000};

        Random random = new Random(42);

        System.out.println(
                "Algorithm,Input,n,time_ms,comparisons");

        for (int n : sizes) {

            int[] randomArray = new int[n];

            for (int i = 0; i < n; i++) {
                randomArray[i] = random.nextInt(100000);
            }

            int k = n / 2;

            runQuickSelect(
                    randomArray, k, "random");

            runDeterministicSelect(
                    randomArray, k, "random");


            int[] sortedArray = randomArray.clone();
            Arrays.sort(sortedArray);

            runQuickSelect(
                    sortedArray, k, "sorted");

            runDeterministicSelect(
                    sortedArray, k, "sorted");
        }
    }

    private static void runQuickSelect(
            int[] original,
            int k,
            String input) {

        int[] array = original.clone();

        Metrics metrics = new Metrics();

        metrics.startTimer();

        QuickSelect.select(array, k, metrics);

        metrics.stopTimer();

        System.out.println(
                "QuickSelect,"
                        + input + ","
                        + array.length + ","
                        + metrics.getTimeMs() + ","
                        + metrics.getComparisons());
    }

    private static void runDeterministicSelect(
            int[] original,
            int k,
            String input) {

        int[] array = original.clone();

        Metrics metrics = new Metrics();

        metrics.startTimer();

        DeterministicSelect.select(
                array, k, metrics);

        metrics.stopTimer();

        System.out.println(
                "DeterministicSelect,"
                        + input + ","
                        + array.length + ","
                        + metrics.getTimeMs() + ","
                        + metrics.getComparisons());
    }
}