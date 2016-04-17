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
