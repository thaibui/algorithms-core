A place for algorithms to live
-------
[![Build Status](https://travis-ci.org/thaibui/algorithms-core.svg?branch=master)](https://travis-ci.org/thaibui/algorithms-core)
[![Coverage Status](https://coveralls.io/repos/github/thaibui/algorithms-core/badge.svg?branch=master)](https://coveralls.io/github/thaibui/algorithms-core?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/62844e59eb9f4dd5835e2ede39360d7a)](https://www.codacy.com/app/thai-bui/algorithms-core)

It's simply a place for algorithms to live. A list of algorithms in this project
is provided below.

The project also includes tests and micro benchmarks for comparisons.

## Sorting Algorithms

### Mergesort

A generic implementation of mergesort algorithm in Java to sort data in a
single machine, with multiple threads and in a distributed environment (TODO).  

#### ParallelMergesort

[ParallelMergesort][parallel_mergesort] is an attempt to improve the default Java's Timsort by parallelizing
the sort tasks in a multi-core machine. Currently, the algorithm improves the default Java 7's sort 
performance by 50% on a Quad core CPU using 8 threads. See more info in this
[blog][improving_java_merge_sort_part2]

[parallel_mergesort]: sort/src/main/java/org/bui/algorithms/sort/ParallelMergesort.java
[improving_java_merge_sort_part2]: http://thaibui.github.io/posts/improving-java-sorting-algorithms-part-2/

## Benchmarks

A comprehensive list of benchmarks and the results is provided below. All the benchmarks
are done on a Macbook Pro (Retina, 15-inch, late 2013) using JMH v1.11.3. 

### Hardware Specs

Component    |  Detail 
-------------|---------
Processor    | 2.3 GHz Intel Core i7
Memory       | 8x2 GB 1600 Mhz DDR3
Graphics     | Intel Iris Pro 1536 MB
OS Version   | OS X 10.9.5
Storage      | 251 GB Flash Storage

### Result

The latest result of all the benchmarks is summarized in this [table]
(benchmark/result/all.csv)

[![Analytics](https://ga-beacon.appspot.com/UA-75323672-2/github/thaibui/algorithms-core?pixel)](https://github.com/thaibui/algorithms-core)
