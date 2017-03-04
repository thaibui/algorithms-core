package org.bui.algorithms;

import org.bui.algorithms.sort.ForkJoinMergesort;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.Arrays;

import static org.bui.algorithms.BenchmarkData.MOVIE_TITLES;
import static org.bui.algorithms.BenchmarkData.RANDOM_INT_ARRAY;
import static org.bui.algorithms.BenchmarkData.STOCK_VALUES;

public class ForkJoinMergesortBenchmark {

  private static ForkJoinMergesort sorter = new ForkJoinMergesort();

  @Benchmark
  public String[] sortMovieTitles() {
    String[] data = Arrays.copyOf(MOVIE_TITLES, MOVIE_TITLES.length);
    return sorter.sort(data, String.class);
  }

  @Benchmark
  public Double[] sortStockValues() {
    Double[] data = Arrays.copyOf(STOCK_VALUES, STOCK_VALUES.length);
    return sorter.sort(data, Double.class);
  }

  @Benchmark
  public Integer[] sortRandomIntValues() {
    Integer[] data = Arrays.copyOf(RANDOM_INT_ARRAY, RANDOM_INT_ARRAY.length);
    return sorter.sort(data, Integer.class);
  }
}
