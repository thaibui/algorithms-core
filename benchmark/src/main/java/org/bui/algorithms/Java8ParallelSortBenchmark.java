package org.bui.algorithms;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.bui.algorithms.BenchmarkUtils.ARRAY_SIZE_LARGE;
import static org.bui.algorithms.BenchmarkUtils.ARRAY_SIZE_MEDIUM;

public class Java8ParallelSortBenchmark {

  // Small array size

  @Benchmark
  public Object sortRandomIntArray01()
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_LARGE);
    Arrays.parallelSort(data);
    return data;
  }

  // Medium array size

  @Benchmark
  public Integer[] sortMediumRandomIntArray01()
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_MEDIUM);
    Arrays.parallelSort(data);
    return data;
  }

  // Large array size

  @Benchmark
  @OutputTimeUnit(TimeUnit.MINUTES)
  public Integer[] sortLargeRandomIntArray01()
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_LARGE);
    Arrays.parallelSort(data);
    return data;
  }
}
