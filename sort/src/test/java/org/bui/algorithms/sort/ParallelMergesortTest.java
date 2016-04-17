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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertArrayEquals;

public class ParallelMergesortTest {

  @Rule
  public final ExpectedException expectedException = ExpectedException.none();

  private static final ParallelMergesort parallelSorter = new ParallelMergesort(
      Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
  );

  @Test
  public void testSortEmptyIterableWorks() throws InterruptedException, ExecutionException {
    assertArrayEquals("Sort empty array should work", new String[]{}, parallelSorter.sort(new String[]{}));
  }

  @Test
  public void testNullArrayOfElementsShouldFail() throws InterruptedException, ExecutionException { // NOPMD
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("array of elements cannot be null");
    parallelSorter.sort(null);
  }

  @Test
  public void testArrayWithNullElementShouldFail() throws InterruptedException, ExecutionException { // NOPMD
    expectedException.expect(ExecutionException.class);
    parallelSorter.sort(new Integer[]{1, 2, null, 5, 3});
  }


  @Test
  public void testSortArrayWithTwoElements() throws InterruptedException, ExecutionException {
    assertArrayEquals("Array with two elements should be sorted correctly",
        new Integer[]{1, 2}, parallelSorter.sort(new Integer[]{2, 1}));
  }

  @Test
  public void testSortArrayWithOneElement() throws InterruptedException, ExecutionException {
    assertArrayEquals("Array with one element should be sorted correctly",
        new Integer[]{1}, parallelSorter.sort(new Integer[]{1}));
  }

  @Test
  public void testSortArrayWithManyElementsShouldBeCorrect() throws InterruptedException, ExecutionException {
    assertArrayEquals("Array in reversed order should be sorted correctly",
        new Integer[]{15,62,105,105},
        parallelSorter.sort(new Integer[]{105, 105, 62, 15})
    );

    assertArrayEquals("Array in random order should be sorted correctly",
        new Integer[]{1,1,1,2,3,4,4,5,6,8,60,123},
        parallelSorter.sort(new Integer[]{8, 1, 4, 2, 4, 1, 5, 60, 1, 3, 6, 123})
    );
  }

  @Test
  public void testSortDifferentDataTypeWorks() throws InterruptedException, ExecutionException {
    assertArrayEquals("Sort array of String should work",
        new String[]{"a", "b", "c"},
        parallelSorter.sort(new String[]{"b", "c", "a"}));

    assertArrayEquals("Sort array of object with custom comparable class should work",
        new House[]{House.of("Jerry"), House.of("Ben"), House.of("Adam")},
        parallelSorter.sort(new House[]{House.of("Jerry"), House.of("Adam"), House.of("Ben")})
    );
  }
}
