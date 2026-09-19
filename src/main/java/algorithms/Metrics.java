package algorithms;

public class Metrics {

    private long comparisons;
    private int currentDepth;
    private int maxDepth;

    private long startTime;
    private long time;


    public void reset() {
        comparisons = 0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        time = 0;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        time = System.nanoTime() - startTime;
    }

    public void addComparison() {
        comparisons++;
    }

    public void enterRecursion() {
        currentDepth++;

        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getTimeNs() {
        return time;
    }

    public double getTimeMs() {
        return time / 1_000_000.0;
    }
}