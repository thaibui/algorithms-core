package org.bui.algorithms.sort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinMergesort {
  private static final int MIN_GRANULARITY = 256;

  private final int granularity;
  private final ForkJoinPool pool;

  public ForkJoinMergesort(final ForkJoinPool pool){
    this(pool, MIN_GRANULARITY);
  }

  public ForkJoinMergesort(final ForkJoinPool pool, final int granularity){
    this.pool = pool;
    this.granularity = granularity;
  }

  public <T extends Comparable> T[] sort(final T[] elements, final Class<T> tClass){
    pool.invoke(new MergesortTask<>(elements, 0, elements.length, granularity, tClass));

    return elements;
  }

  private static class MergesortTask<T extends Comparable> extends RecursiveAction {
    private final T[] elements;
    private final int startIdx;
    private final int endIdx;
    private final int granularity;
    private final Class<T> tClass;

    public MergesortTask(final T[] elements, final int startIdx, final int endIdx, final int granularity, final Class<T> tClass){
      this.elements = elements;
      this.startIdx = startIdx;
      this.endIdx = endIdx;
      this.granularity = granularity;
      this.tClass = tClass;
    }

    @Override
    protected void compute() {
      int length = endIdx - startIdx;
      int midIdx = startIdx + length / 2;
      if (length < granularity) {
        Arrays.sort(elements, startIdx, endIdx);
      } else {
        invokeAll(
            new MergesortTask<>(elements, startIdx, midIdx, granularity, tClass),
            new MergesortTask<>(elements, midIdx, endIdx, granularity, tClass)
        );
      }

      Mergesort.merge(elements, startIdx, midIdx - startIdx, midIdx, endIdx - midIdx, tClass);
    }
  }
}
