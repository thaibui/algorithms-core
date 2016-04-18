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

package org.bui.algorithms;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.bui.algorithms.BenchmarkUtils.ARRAY_SIZE_LARGE;
import static org.bui.algorithms.BenchmarkUtils.ARRAY_SIZE_MEDIUM;
import static org.bui.algorithms.BenchmarkUtils.ARRAY_SIZE_SMALL;
import static org.bui.algorithms.BenchmarkUtils.WORDS_SMALL_ARRAY_01;
import static org.bui.algorithms.BenchmarkUtils.WORDS_SMALL_ARRAY_02;

public class JavasortBenchmark {

  @Benchmark
  public String[] sortSimpleStringArray01() {
    String[] data = Arrays.copyOf(WORDS_SMALL_ARRAY_01, WORDS_SMALL_ARRAY_01.length);
    Arrays.sort(data);
    return data;
  }

  @Benchmark
  public String[] sortSimpleStringArray02() {
    String[] data = Arrays.copyOf(WORDS_SMALL_ARRAY_02, WORDS_SMALL_ARRAY_02.length);
    Arrays.sort(data);
    return data;
  }

  @Benchmark
  public Integer[] sortRandomIntArray01(){
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_SMALL);
    Arrays.sort(data);
    return data;
  }

  @Benchmark
  public Integer[] sortMediumRandomIntArray01(){
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_MEDIUM);
    Arrays.sort(data);
    return data;
  }

  @Benchmark
  @OutputTimeUnit(TimeUnit.MINUTES)
  public Integer[] sortLargeRandomIntArray01(){
    Integer[] data = BenchmarkUtils.randomArray(ARRAY_SIZE_LARGE);
    Arrays.sort(data);
    return data;
  }
}
