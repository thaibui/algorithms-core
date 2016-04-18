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

import java.util.Date;
import java.util.Random;

public class BenchmarkUtils {

  public static final int ARRAY_SIZE_SMALL = 10000;
  public static final int ARRAY_SIZE_MEDIUM = 200000;
  public static final int ARRAY_SIZE_LARGE = 1000000;

  public static final String[] WORDS_SMALL_ARRAY_01 = new String[]{
      "zoo", "home", "car", "paper", "bowl", "data", "clean", "mountain", "fast", "correct", "how", "paper",
      "more", "television", "grade", "email", "the Internet", "country", "The United States of America"
  };

  public static final String[] WORDS_SMALL_ARRAY_02 = new String[]{
      "zoo", "home", "car", "paper", "bowl", "data", "clean", "mountain", "fast", "correct", "how", "paper",
      "more", "television", "grade", "email", "the Internet", "country", "The United States of America",
      "stop", "hop", "shop", "lop", "not", "lot", "sot", "top", "rot", "pot", "odd", "mot", "cost", "tod",
      "more", "television", "grade", "email", "the Internet", "country", "The United States of America",
      "zoo", "home", "car", "paper", "bowl", "data", "clean", "mountain", "fast", "correct", "how", "paper",
      "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
      "u", "v", "w", "y", "x", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!#", "`", ")(", "$"
  };

  public static Integer[] randomArray(int size){
    Integer[] a = new Integer[size];
    Random rand = new Random(new Date().getTime());
    for (int i = 0; i < size; i++){
      a[i] = rand.nextInt();
    }
    return a;
  }
}
