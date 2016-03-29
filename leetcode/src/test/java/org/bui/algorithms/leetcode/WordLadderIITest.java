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

package org.bui.algorithms.leetcode;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.bui.algorithms.leetcode.WordLadderII.findLadders;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class WordLadderIITest {

  @Test
  public void testNoShortestPath() {
    List<List<String>> solution = findLadders("hot", "dog", new HashSet<>(Arrays.asList("hot", "dog")));
    assertTrue("Result should be empty", solution.isEmpty());

  }

  @SuppressWarnings("unchecked")
  @Test
  public void testMultipleSolutions() {
    List<List<String>> solution1 = findLadders("hit", "cog", new HashSet<>(Arrays.asList("hot","dot","dog","lot","log")));
    assertThat("Result should contain correct solutions", solution1, Matchers.containsInAnyOrder(
        Arrays.asList("hit", "hot", "lot", "log", "cog"),
        Arrays.asList("hit", "hot", "dot", "dog", "cog")
    ));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testMultipleSolutionsSpecialCase() {
    List<List<String>> solution1 = findLadders("hit", "coh", new HashSet<>(Arrays.asList("hot","dot","dog","lot","log", "cog")));
    assertThat("Result should contain correct solutions", solution1, Matchers.containsInAnyOrder(
        Arrays.asList("hit", "hot", "lot", "log", "cog", "coh"),
        Arrays.asList("hit", "hot", "dot", "dog", "cog", "coh")
    ));
  }
}