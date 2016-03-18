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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

public class MergesortTest {

  @Rule
  public final ExpectedException expectedException = ExpectedException.none();

  @Test
  public void testSortEmptyIterableWorks() {
    assertArrayEquals("Sort empty array should work", new String[]{}, Mergesort.sort(new String[]{}, String.class));
  }

  @Test
  public void testNullArrayOfElementsShouldFail() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("array of elements cannot be null");
    Mergesort.sort(null, Comparable.class);
  }

  @Test
  public void testArrayWithNullElementShouldFail() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage(String.format("array element cannot be null, found null element at index %d", 2));
    Mergesort.sort(new Integer[]{1, 2, null, 3, 5}, Integer.class);
  }


  @Test
  public void testSortArrayWithTwoElements() {
    assertArrayEquals("Array with two elements should be sorted correctly",
        new Integer[]{1, 2}, Mergesort.sort(new Integer[]{2, 1}, Integer.class));
  }

  @Test
  public void testSortArrayWithOneElement() {
    assertArrayEquals("Array with one element should be sorted correctly",
        new Integer[]{1}, Mergesort.sort(new Integer[]{1}, Integer.class));
  }

  @Test
  public void testSortArrayWithManyElementsShouldBeCorrect() {
    assertArrayEquals("Array in reversed order should be sorted correctly",
        new Integer[]{15,62,105,105},
        Mergesort.sort(new Integer[]{105, 105, 62, 15}, Integer.class)
    );

    assertArrayEquals("Array in random order should be sorted correctly",
        new Integer[]{1,1,1,2,3,4,4,5,6,8,60,123},
        Mergesort.sort(new Integer[]{8, 1, 4, 2, 4, 1, 5, 60, 1, 3, 6, 123}, Integer.class)
    );
  }

  @Test
  public void testSortDifferentDataTypeWorks() {
    assertArrayEquals("Sort array of String should work",
        new String[]{"a", "b", "c"},
        Mergesort.sort(new String[]{"b", "c", "a"}, String.class));

    assertArrayEquals("Sort array of object with custom comparable class should work",
        new House[]{House.of("Jerry"), House.of("Ben"), House.of("Adam")},
        Mergesort.sort(new House[]{House.of("Jerry"), House.of("Adam"), House.of("Ben")}, House.class)
    );
  }

  @Test
  public void testConstruction(){
    assertNotNull(new Mergesort());
  }

  private static class House implements Comparable {
    private final String name;

    public House(String name){
      if (null == name){
        throw new NullPointerException("House's name cannot be null");
      }

      this.name = name;
    }

    public static House of(String name){
      return new House(name);
    }

    @Override
    public int compareTo(Object o) {
      return ((House) o).name.compareTo(name); // descending order
    }

    @Override
    public String toString(){
      return name;
    }

    @Override
    public boolean equals(Object o){
      return o != null
          && getClass().equals(o.getClass())
          && name.equals(((House)o).name);
    }

    @Override
    public int hashCode(){
      return name.hashCode();
    }
  }
}
