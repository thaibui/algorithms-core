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

import org.bui.algorithms.sort.Mergesort;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.Arrays;

import static org.bui.algorithms.BenchmarkData.MOVIE_TITLES;
import static org.bui.algorithms.BenchmarkData.RANDOM_INT_ARRAY;
import static org.bui.algorithms.BenchmarkData.STOCK_VALUES;

public class MergesortBenchmark {

  @Benchmark
  public String[] sortMovieTitles() {
    String[] data = Arrays.copyOf(MOVIE_TITLES, MOVIE_TITLES.length);
    return Mergesort.sort(data, String.class);
  }

  @Benchmark
  public Double[] sortStockValues() {
    Double[] data = Arrays.copyOf(STOCK_VALUES, STOCK_VALUES.length);
    return Mergesort.sort(data, Double.class);
  }

  @Benchmark
  public Integer[] sortRandomIntValues() {
    Integer[] data = Arrays.copyOf(RANDOM_INT_ARRAY, RANDOM_INT_ARRAY.length);
    return Mergesort.sort(data, Integer.class);
  }
//
//  @Benchmark
//  public String[] sortSimpleStringArray02() {
//    String[] data = Arrays.copyOf(WORDS_SMALL_ARRAY_02, WORDS_SMALL_ARRAY_02.length);
//    return Mergesort.sort(data, String.class);
//  }
//
//  @Benchmark
//  public Integer[] sortRandomIntArray01(){
//    Integer[] data =  Arrays.copyOf(TINY_ARRAY, TINY_ARRAY.length);
//    return Mergesort.sort(data, Integer.class);
//  }
//
//  @Benchmark
//  public Integer[] sortRandomIntArray02(){
//    Integer[] data =  Arrays.copyOf(SMALL_ARRAY, SMALL_ARRAY.length);
//    return Mergesort.sort(data, Integer.class);
//  }
//
//  @Benchmark
//  public Integer[] sortRandomIntArray03(){
//    Integer[] data =  Arrays.copyOf(LARGE_ARRAY, LARGE_ARRAY.length);
//    return Mergesort.sort(data, Integer.class);
//  }
}
