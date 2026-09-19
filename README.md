# DAA Assignment 1 — Fast Sorting & Selection Engine

## Description

This project implements three Divide-and-Conquer algorithms for large integer arrays:

- MergeSort
- QuickSort
- QuickSelect

The project measures execution time, number of comparisons, and maximum recursion depth.

## Algorithms

### MergeSort

MergeSort uses one reusable helper array and an Insertion Sort cutoff for subarrays of 15 elements or fewer.

Time complexity:

- Best: Θ(n log n)
- Average: Θ(n log n)
- Worst: Θ(n log n)

### QuickSort

QuickSort uses:

- Random pivot selection
- 3-way partitioning for duplicate values
- Smaller-side-first recursion
- A loop for the larger partition

This keeps the recursion depth bounded and prevents StackOverflowError on large sorted arrays.

Average time complexity: Θ(n log n).

### QuickSelect

QuickSelect finds the k-th smallest element using the same 3-way partitioning approach as QuickSort.

After partitioning, it continues only in the part that contains position k.

Average time complexity: Θ(n).

## Metrics

The Metrics class measures:

- Execution time using System.nanoTime()
- Number of comparisons
- Maximum recursion depth

The Metrics object is passed directly to the algorithms.

## Benchmark

The benchmark uses the following array sizes:

- 1,000
- 10,000
- 100,000
- 1,000,000

Input types:

- Random integers
- Already sorted arrays
- Arrays with duplicates from 0 to 9

Each case is executed 5 times, and the median execution time is saved.

Results are stored in `results.csv`.

The CSV columns are:

`algorithm,input,n,time_ms,comparisons,max_depth`

## Testing

JUnit 5 tests are used to check the correctness of the algorithms.

The tests check:

- Correct sorting results
- At least 100 random arrays
- Empty arrays
- One-element arrays
- Arrays where all elements are equal
- Already sorted arrays
- QuickSort recursion depth
- QuickSelect correctness

Sorting results are compared with `Arrays.sort()`.

QuickSort is tested on a sorted array of 100,000 elements. The maximum recursion depth must satisfy:

`maxDepth <= 2 * log2(n)`

QuickSelect is tested on random arrays by comparing its result with the corresponding element of the sorted array.

## Project Structure

- `src/main/java/algorithms/MergeSort.java`
- `src/main/java/algorithms/QuickSort.java`
- `src/main/java/algorithms/QuickSelect.java`
- `src/main/java/algorithms/Metrics.java`
- `src/main/java/algorithms/Benchmark.java`
- `src/test/java/algorithms/MergeSortTest.java`
- `src/test/java/algorithms/QuickSortTest.java`
- `src/test/java/algorithms/QuickSelectTest.java`
- `results.csv`
- `README.md`
- `pom.xml`

## How to Build

Run:

`mvn clean test`

## How to Run Tests

Run:

`mvn test`

This runs all JUnit 5 tests.

## How to Run the Benchmark

Run the `Benchmark` class from IntelliJ IDEA.

After execution, the benchmark creates the `results.csv` file.

The file contains results for MergeSort, QuickSort and QuickSelect.

## Git Workflow

The project uses the following feature branches:

- `feature/mergesort`
- `feature/quicksort`
- `feature/select`
- `feature/metrics`

The final working version is prepared for the `main` branch.

Release tag:

`v1.0`

## Results

The benchmark results are stored in `results.csv`.

The benchmark includes:

- MergeSort
- QuickSort
- QuickSelect

Input types:

- Random
- Sorted
- Duplicates

Array sizes:

- 1,000
- 10,000
- 100,000
- 1,000,000

Each case is executed 5 times, and the median execution time is saved.