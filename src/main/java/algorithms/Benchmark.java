package algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static final int[] SIZES = {
            1_000,
            10_000,
            100_000,
            1_000_000
    };

    private static final String[] INPUT_TYPES = {
            "random",
            "sorted",
            "duplicates"
    };

    private static final int RUNS = 5;

    public static void main(String[] args) throws IOException {

        FileWriter writer = new FileWriter("results.csv");

        writer.write("algorithm,input,n,time_ms,comparisons,max_depth\n");

        for (int size : SIZES) {

            for (String inputType : INPUT_TYPES) {

                int[] original = createArray(size, inputType);

                runMergeSort(writer, original, inputType);
                runQuickSort(writer, original, inputType);
                runQuickSelect(writer, original, inputType);
            }
        }

        writer.close();

        System.out.println("Benchmark finished.");
        System.out.println("Results saved to results.csv");
    }

    private static int[] createArray(int size, String type) {

        int[] array = new int[size];
        Random random = new Random(42);

        if (type.equals("random")) {

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1_000_000);
            }

        } else if (type.equals("sorted")) {

            for (int i = 0; i < size; i++) {
                array[i] = i;
            }

        } else if (type.equals("duplicates")) {

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(10);
            }
        }

        return array;
    }

    private static void runMergeSort(
            FileWriter writer,
            int[] original,
            String inputType) throws IOException {

        long[] times = new long[RUNS];
        long comparisons = 0;
        int maxDepth = 0;

        for (int run = 0; run < RUNS; run++) {

            int[] array = original.clone();

            Metrics metrics = new Metrics();

            metrics.startTimer();

            MergeSort.sort(array, metrics);

            metrics.stopTimer();

            times[run] = metrics.getTimeNs();
            comparisons = metrics.getComparisons();
            maxDepth = metrics.getMaxDepth();
        }

        long median = getMedian(times);

        writeResult(
                writer,
                "MergeSort",
                inputType,
                original.length,
                median,
                comparisons,
                maxDepth
        );
    }

    private static void runQuickSort(
            FileWriter writer,
            int[] original,
            String inputType) throws IOException {

        long[] times = new long[RUNS];
        long comparisons = 0;
        int maxDepth = 0;

        for (int run = 0; run < RUNS; run++) {

            int[] array = original.clone();

            Metrics metrics = new Metrics();

            metrics.startTimer();

            QuickSort.sort(array, metrics);

            metrics.stopTimer();

            times[run] = metrics.getTimeNs();
            comparisons = metrics.getComparisons();
            maxDepth = metrics.getMaxDepth();
        }

        long median = getMedian(times);

        writeResult(
                writer,
                "QuickSort",
                inputType,
                original.length,
                median,
                comparisons,
                maxDepth
        );
    }

    private static void runQuickSelect(
            FileWriter writer,
            int[] original,
            String inputType) throws IOException {

        long[] times = new long[RUNS];
        long comparisons = 0;
        int maxDepth = 0;

        int k = original.length / 2;

        for (int run = 0; run < RUNS; run++) {

            int[] array = original.clone();

            Metrics metrics = new Metrics();

            metrics.startTimer();

            QuickSelect.select(array, k, metrics);

            metrics.stopTimer();

            times[run] = metrics.getTimeNs();
            comparisons = metrics.getComparisons();
            maxDepth = metrics.getMaxDepth();
        }

        long median = getMedian(times);

        writeResult(
                writer,
                "QuickSelect",
                inputType,
                original.length,
                median,
                comparisons,
                maxDepth
        );
    }

    private static long getMedian(long[] values) {

        long[] copy = values.clone();

        Arrays.sort(copy);

        return copy[copy.length / 2];
    }

    private static void writeResult(
            FileWriter writer,
            String algorithm,
            String inputType,
            int n,
            long timeNs,
            long comparisons,
            int maxDepth) throws IOException {

        double timeMs = timeNs / 1_000_000.0;

        writer.write(
                algorithm + "," +
                        inputType + "," +
                        n + "," +
                        timeMs + "," +
                        comparisons + "," +
                        maxDepth + "\n"
        );
    }
}