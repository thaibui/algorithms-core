package org.bui.algorithms.sort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinMergesort {
  private static final int MIN_GRANULARITY = 256;

  private final ForkJoinPool pool;

  public ForkJoinMergesort() {
    this(new ForkJoinPool((Runtime.getRuntime().availableProcessors())));
  }

  public ForkJoinMergesort(final ForkJoinPool pool){
    this.pool = pool;
  }

  public <T extends Comparable> T[] sort(final T[] elements, final Class<T> tClass, final int granularity){
    pool.invoke(new MergesortTask<>(elements, 0, elements.length, Math.max(granularity, MIN_GRANULARITY), tClass));

    return elements;
  }

  public <T extends Comparable> T[] sort(final T[] elements, final Class<T> tClass){
    return sort(elements, tClass, (int) (elements.length / Math.log(elements.length)));
  }

  private static class MergesortTask<T extends Comparable> extends RecursiveAction {
    private final T[] elements;
    private final int startIdx;
    private final int endIdx;
    private final int granularity;
    private final Class<T> tClass;

    MergesortTask(final T[] elements, final int startIdx, final int endIdx, final int granularity, final Class<T> tClass){
      this.elements = elements;
      this.startIdx = startIdx;
      this.endIdx = endIdx;
      this.granularity = granularity;
      this.tClass = tClass;
    }

    @Override
    protected void compute() {
      int length = endIdx - startIdx;

      if (length < granularity) {
        Arrays.sort(elements, startIdx, endIdx);
      } else {
        int midIdx = startIdx + length / 2;

        invokeAll(
            new MergesortTask<>(elements, startIdx, midIdx, granularity, tClass),
            new MergesortTask<>(elements, midIdx, endIdx, granularity, tClass)
        );

        Mergesort.merge(elements, startIdx, midIdx - startIdx, midIdx, endIdx - midIdx, tClass);
      }
    }
  }
}
