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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class WordLadderII {

  public static List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {

    // dictionary of all words
    wordList.add(beginWord);
    wordList.add(endWord);
    int n = wordList.size();

    // create a map of each word to its connected words
    Map<String, Set<String>> connections = new HashMap<>(n);

    // breath-first search layer by layer
    Set<String> currentLayer = Collections.singleton(beginWord);
    Set<String> nextLayer;
    boolean done = false;

    while(!done && currentLayer.size() > 0){
      wordList.removeAll(currentLayer);
      nextLayer = new HashSet<>();

      for (String cur : currentLayer){
        Set<String> nextWords = connections(cur, wordList);

        if (nextWords.contains(endWord)){
          done = true;
        }

        if (connections.get(cur) == null){
          connections.put(cur, new HashSet<String>());
        }

        connections.get(cur).addAll(nextWords);
        nextLayer.addAll(nextWords);
      }

      currentLayer = nextLayer;
    }

    // depth-first search to compute the shortest paths
    List<List<String>> result = new ArrayList<>();
    Stack<String> stack = new Stack<>();
    stack.push(beginWord);
    result(beginWord, endWord, connections, result, stack);

    return result;
  }

  private static void result(String word,
                             String wordToFind,
                             Map<String, Set<String>> connections,
                             List<List<String>> result,
                             Stack<String> stack){
    if (wordToFind.equals(word)){
      result.add(Arrays.asList(stack.toArray(new String[stack.size()])));
    } else {
      Set<String> set = connections.get(word);
      if (set != null){
        for (String r : set){
          stack.push(r);
          result(r, wordToFind, connections, result, stack);
          stack.pop();
        }
      }
    }
  }

  public static Set<String> connections(String word, Set<String> dict){
    Set<String> set = new HashSet<>();
    char[] chars = word.toCharArray();

    for (int i = 0; i < chars.length; i++){
      char original = chars[i];
      char c = 'a';

      for (; c < original; c++){
        chars[i] = c;
        String w = new String(chars);
        if (dict.contains(w)) set.add(w);
      }

      for (c += 1; c < 'z'; c++){
        chars[i] = c;
        String w = new String(chars);
        if (dict.contains(w)) set.add(w);
      }

      chars[i] = original;
    }

    return set;
  }
}