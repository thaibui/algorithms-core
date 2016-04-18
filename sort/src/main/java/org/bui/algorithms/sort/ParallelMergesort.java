/*
 * Copyright 2016. Thai Bui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bui.algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * This class parallelize the default Java {@link Arrays#sort} function by leveging
 * multiple threads provided by an {@link ExecutorService}.
 */
public class ParallelMergesort {
  public static final int MIN_GRANULARITY = 256;
  private final ExecutorService executor;
  private final int granularity;

  public ParallelMergesort(ExecutorService executor){
    this(executor, MIN_GRANULARITY);
  }

  public ParallelMergesort(final ExecutorService executor, final int granularity){
    if (null == executor){
      throw new IllegalArgumentException("executor cannot be null");
    }

    if (granularity < MIN_GRANULARITY){
      throw new IllegalArgumentException("min granularity value is " +
          MIN_GRANULARITY + ", got " + granularity);
    }

    this.executor = executor;
    this.granularity = granularity;
  }

  public <T extends Comparable> T[] sort(final T[] elements, Class<T> tClass)
      throws InterruptedException, ExecutionException {
    if (null == elements){
      throw new IllegalArgumentException("array of elements cannot be null");
    }

    if (elements.length < 2){
      return elements;
    }

    // Construct a list of tasks and invoke them all
    for (Future<T> f : executor.invokeAll(build(elements, tClass, 0, elements.length))){
      f.get();
    }

    return elements;
  }

  private <T extends Comparable> List<Callable<T>> build(T[] elements, Class<T> tClass, int start, int end){
    List<Callable<T>> tasks = new ArrayList<>();

    tasks.add(build(elements, start, end, tasks, tClass));

    return tasks;
  }

  private <T extends Comparable> Callable<T> build(
      T[] elements,
      int start,
      int end,
      List<Callable<T>> tasks,
      Class<T> tClass){

    int len = end - start;
    if (len < granularity) {
      return new SortTask<>(elements, start, end);
    } else {
      int mid = len / 2;
      CountDownLatch latch = new CountDownLatch(2);

      tasks.add(new ForkTask<>(latch, build(elements, start, start + mid, tasks, tClass)));
      tasks.add(new ForkTask<>(latch, build(elements, start + mid, end, tasks, tClass)));

      return new JoinTask<>(latch, new MergeTask<>(elements, start, start + mid, start + mid, end, tClass));
    }
  }

  private static abstract class MergesortTask<T extends Comparable> implements Callable<T> {
    protected final T[] elements;
    protected final int startIdx;
    protected final int endIdx;

    protected MergesortTask(T[] elements, int startIdx, int endIdx) {
      this.elements = elements;
      this.startIdx = startIdx;
      this.endIdx = endIdx;
    }
  }

  private static class SortTask<T extends Comparable> extends MergesortTask<T> {

    protected SortTask(T[] elements, int startIdx, int endIdx) {
      super(elements, startIdx, endIdx);
    }

    @Override
    public T call() throws Exception {
      Arrays.sort(elements, startIdx, endIdx);

      return null;
    }
  }

  private static class ForkTask<T> implements Callable<T> {
    private final CountDownLatch latch;
    private final Callable<T> task;

    protected ForkTask(CountDownLatch latch, Callable<T> task) {
      this.latch = latch;
      this.task = task;
    }

    @Override
    public T call() throws Exception {
      try {
        return task.call();
      } catch (Exception e){
        throw new ExecutionException("Error while trying to execute forked task " + task.getClass().toString(), e);
      } finally {
        latch.countDown();
      }
    }
  }

  private static class JoinTask<T> implements Callable<T> {
    private final CountDownLatch latch;
    private final Callable<T> task;

    private JoinTask(CountDownLatch latch, Callable<T> task) {
      this.latch = latch;
      this.task = task;
    }

    @Override
    public T call() throws Exception {
      latch.await();

      return task.call();
    }
  }

  private static class MergeTask<T extends Comparable> extends MergesortTask<T> {
    private final Class<T> tClass;
    private final int leftLength;
    private final int rightLength;

    private MergeTask(
        T[] elements,
        int leftStartIdx,
        int leftEndIdx,
        int rightStartIdx,
        int rightEndIdx,
        Class<T> tClass) {
      super(elements, leftStartIdx, rightEndIdx);

      this.leftLength = leftEndIdx - leftStartIdx;
      this.rightLength = rightEndIdx - rightStartIdx;
      this.tClass = tClass;
    }

    @Override
    public T call() throws Exception {
      Mergesort.merge(
          elements,
          startIdx, leftLength,
          startIdx + leftLength, rightLength,
          tClass
      );

      return null;
    }
  }
}
