package org.bui.algorithms.mergesort;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MergesortTest {

  @Test
  public void testSortEmptyIterableWorks() {
    assertArrayEquals("Sort empty array should work", new String[]{}, Mergesort.sort(new String[]{}));
  }

}
