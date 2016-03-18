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

import java.lang.reflect.Array;

/**
 * A simple implementation of the mergesort algorithm. This implementation tries its best to do an in-place
 * sort and merge, however, the in-place merge is still a work in progress and incomplete.
 *
 * TODO Implement in-place merge phase
 */
public class Mergesort {

  /**
   * Perform an in-place merge and sort on a given array of {@link Comparable} element. This method tries
   * not to increase the memory footprint by performing an in-place sort & merge.
   *
   * @param elements The array of {@link Comparable} element
   * @param classOf The class of the element
   * @param <T> The generic type of the element
   * @return The original array with its element sorted using the mergesort algorithm
   */
  public static <T extends Comparable> T[] sort(T[] elements, Class<T> classOf) {
    if (null == elements){
      throw new NullPointerException("array of elements cannot be null");
    }

    if (elements.length > 1){
      int mid = (int)Math.floor(elements.length / 2);
      sort(elements, 0, mid, mid, elements.length - mid, classOf);
    }

    return elements;
  }

  private static <T extends Comparable> void sort(T[] a, int leftIdx, int leftLength, int rightIdx, int rightLength, Class<T> classOf) {
    if (leftLength > 2) {
      int mid = (int)Math.floor(leftLength / 2);
      sort(a, leftIdx, mid, leftIdx + mid, leftLength - mid, classOf);
    } else if (leftLength == 2){
      compareSwap(a, leftIdx, leftIdx + 1);
    }

    if (rightLength > 2){
      int mid = (int)Math.floor(rightLength / 2);
      sort(a, rightIdx, mid, rightIdx + mid, rightLength - mid, classOf);
    } else if (rightLength == 2){
      compareSwap(a, rightIdx, rightIdx + 1);
    }

    merge(a, leftIdx, leftLength, rightIdx, rightLength, classOf);
  }

  @SuppressWarnings("unchecked")
  private static <T extends Comparable> void merge(T[] a, int leftIdx, int leftLength, int rightIdx, int rightLength, Class<T> classOf){
    T[] tmp = (T[]) Array.newInstance(classOf, leftLength + rightLength);
    int left = leftIdx;
    int right = rightIdx;
    int idx = 0;

    while (idx < leftLength + rightLength){
      if (left == leftIdx + leftLength) {
        tmp[idx] = a[right];
        right++;
      }

      else if (right == rightIdx + rightLength) {
        tmp[idx] = a[left];
        left++;
      }

      else if (compare(a, left, right) < 0) {
        tmp[idx] = a[left];
        left++;
      }

      else {
        tmp[idx] = a[right];
        right++;
      }

      idx++;
    }

    System.arraycopy(tmp, 0, a, leftIdx, tmp.length);
  }

  private static <T extends Comparable> void compareSwap(T[] a, int l, int r){
    if (compare(a, l, r) > 0){
      swap(a, l, r);
    }
  }

  @SuppressWarnings("unchecked")
  private static <T extends Comparable> int compare(T[] a, int l, int r){
    if (null == a[l] || null == a[r]) {
      throw new NullPointerException(
          String.format("array element cannot be null, found null element at index %d", a[l] == null ? l : r)
      );
    }

    return a[l].compareTo(a[r]);
  }

  private static <T> void swap(T[] a, int l, int r){
    T tmp = a[l];
    a[l] = a[r];
    a[r] = tmp;
  }
}