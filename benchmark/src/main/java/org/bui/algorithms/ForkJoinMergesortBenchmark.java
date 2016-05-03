package org.bui.algorithms;

import org.bui.algorithms.sort.ForkJoinMergesort;
import org.bui.algorithms.sort.ParallelMergesort;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static org.bui.algorithms.BenchmarkUtils.ARRAY_SIZE_LARGE;
import static org.bui.algorithms.BenchmarkUtils.ARRAY_SIZE_MEDIUM;
import static org.bui.algorithms.BenchmarkUtils.ARRAY_SIZE_SMALL;

public class ForkJoinMergesortBenchmark {

  // Small array size

  @Benchmark
  public Integer[] sortRandomIntArray01_04Threads(Sorter4Thread threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_LARGE);
    return threads.sorter().sort(data, Integer.class);
  }

  @Benchmark
  public Integer[] sortRandomIntArray01_08Threads(Sorter8Thread threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_SMALL);
    return threads.sorter().sort(data, Integer.class);
  }

  @Benchmark
  public Integer[] sortRandomIntArray01_16Threads(Sorter16Thread threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_SMALL);
    return threads.sorter().sort(data, Integer.class);
  }

  // Medium array size

  @Benchmark
  public Integer[] sortMediumRandomIntArray01_04Threads(Sorter4Thread threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_MEDIUM);
    return threads.sorter().sort(data, Integer.class);
  }

  @Benchmark
  public Integer[] sortMediumRandomIntArray01_08Threads(Sorter8Thread threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_MEDIUM);
    return threads.sorter().sort(data, Integer.class);
  }

  @Benchmark
  public Integer[] sortMediumRandomIntArray01_16Threads(Sorter16Thread threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_MEDIUM);
    return threads.sorter().sort(data, Integer.class);
  }

  // Large array size

  @Benchmark
  @OutputTimeUnit(TimeUnit.MINUTES)
  public Integer[] sortLargeRandomIntArray01_08Threads(Sorter8Thread threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_LARGE);
    return threads.sorter().sort(data, Integer.class);
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MINUTES)
  public Integer[] sortLargeRandomIntArray01_16Threads(Sorter16Thread threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_LARGE);
    return threads.sorter().sort(data, Integer.class);
  }

  // Different granularity level

  @Benchmark
  public Integer[] sortLargeRandomIntArray01_4Threads_1000Granularity(Sorter4Thread1000Granularity threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_LARGE);
    return threads.sorter().sort(data, Integer.class);
  }

  @Benchmark
  public Integer[] sortLargeRandomIntArray01_4Threads_5000Granularity(Sorter4Thread5000Granularity threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_LARGE);
    return threads.sorter().sort(data, Integer.class);
  }

  @Benchmark
  public Integer[] sortLargeRandomIntArray01_4Threads_20000Granularity(Sorter4Thread20000Granularity threads)
      throws ExecutionException, InterruptedException {
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_MEDIUM);
    return threads.sorter().sort(data, Integer.class);
  }

  // Benchmark sorters setup & tear down

  public static class Sorter4Thread extends SorterSetup {
    public Sorter4Thread(){
      super(4, ParallelMergesort.MIN_GRANULARITY);
    }
  }

  public static class Sorter8Thread extends SorterSetup {
    public Sorter8Thread(){
      super(8, ParallelMergesort.MIN_GRANULARITY);
    }
  }

  public static class Sorter16Thread extends SorterSetup {
    public Sorter16Thread(){
      super(16, ParallelMergesort.MIN_GRANULARITY);
    }
  }

  public static class Sorter4Thread1000Granularity extends SorterSetup {
    public Sorter4Thread1000Granularity(){
      super(4, 1000);
    }
  }

  public static class Sorter4Thread5000Granularity extends SorterSetup {
    public Sorter4Thread5000Granularity(){
      super(4, 5000);
    }
  }

  public static class Sorter4Thread20000Granularity extends SorterSetup {
    public Sorter4Thread20000Granularity(){
      super(4, 20000);
    }
  }

  @State(Scope.Benchmark)
  public static class SorterSetup {
    private ForkJoinPool pool;
    private ForkJoinMergesort sorter;
    private final int numThreads;
    private final int granularity;

    public SorterSetup(int numThreads, int granularity) {
      this.numThreads = numThreads;
      this.granularity = granularity;
    }

    @Setup(Level.Trial)
    public void setup(){
      pool = new ForkJoinPool(numThreads);
      sorter = new ForkJoinMergesort(pool, granularity);
    }

    @TearDown(Level.Trial)
    public void shutdown(){
      pool.shutdownNow();
    }

    public ForkJoinMergesort sorter() {
      return sorter;
    }
  }
}
