package org.bui.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinMergesortTest {

  private static ForkJoinMergesort sorter =
      new ForkJoinMergesort(new ForkJoinPool(Runtime.getRuntime().availableProcessors()));

  @Test
  public void testEmptyArrayShouldWork() {
    Assert.assertArrayEquals(new String[]{}, sorter.sort(new String[]{}, String.class));
  }

  @Test
  public void testArrayWithOneElementWorks() {
    Assert.assertArrayEquals(new String[]{"abc"}, sorter.sort(new String[]{"abc"}, String.class));
  }

  @Test
  public void testArrayWithTwoElementsWorks() {
    Assert.assertArrayEquals(new String[]{"abc", "cba"}, sorter.sort(new String[]{"cba", "abc"}, String.class));
  }

  @Test
  public void testArrayWithManyElementsWorks() {
    Assert.assertArrayEquals(
        new String[]{"a", "aa", "aac", "abc", "c", "cb"},
        sorter.sort(new String[]{"cb", "abc", "c", "aac", "a", "aa"}, String.class)
    );
  }

  @Test
  public void testArrayInReverseOrderSortedCorrectly() {
    Assert.assertArrayEquals(
        new String[]{"1", "2", "3", "4", "5"},
        sorter.sort(new String[]{"5", "4", "3", "2", "1"}, String.class)
    );
  }
}
