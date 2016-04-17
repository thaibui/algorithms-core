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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * This class parallelize the default Java {@link Arrays#sort} function by leveging
 * multiple threads provided by an {@link Executor}.
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

  public <T extends Comparable> T[] sort(final T[] elements) throws InterruptedException, ExecutionException {
    if (null == elements){
      throw new IllegalArgumentException("array of elements cannot be null");
    }

    // Construct a list of tasks to run
    List<Callable<T>> tasks = new ArrayList<>();
    build(elements, 0, elements.length, tasks);

    // Invoke and make sure that all tasks are complete
    List<Future<T>> futures = executor.invokeAll(tasks);
    for (Future<T> f : futures){
      f.get();
    }

    return elements;
  }

  private <T extends Comparable> void build(T[] elements, int start, int end, List<Callable<T>> tasks){
    if (end - start + 1 < granularity) {
      tasks.add(new SortTask<>(elements, start, end));
    } else {
      int mid = (end - start) / 2;
      build(elements, start, mid, tasks);
      build(elements, mid, end, tasks);
    }
  }

  private static class SortTask<T extends Comparable> implements Callable<T> {
    private final T[] elements;
    private final int start;
    private final int end;

    public SortTask(T[] elements, int start, int end){
      this.elements = elements;
      this.start = start;
      this.end = end;
    }

    @Override
    public T call() throws Exception {
      Arrays.sort(elements, start, end);
      return null;
    }
  }
}
