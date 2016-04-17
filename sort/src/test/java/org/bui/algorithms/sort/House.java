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

public class House implements Comparable {
  private final String name;

  public House(String name){
    if (null == name){
      throw new IllegalArgumentException("House's name cannot be null");
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
