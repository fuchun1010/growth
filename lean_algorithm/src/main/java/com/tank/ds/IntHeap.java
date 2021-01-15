package com.tank.ds;

/**
 * @author tank198435163.com
 */
public class IntHeap {

  public IntHeap(int capacity) {
    this.array = new int[capacity];
    this.capacity = capacity;
  }

  public void add(int data) {
    this.array[size] = data;
    this.up(size);
    size++;
  }

  public int[] obtainArray() {
    return this.array;
  }

  private void up(int index) {
    while (index > 0 && this.array[index] > this.array[parentIndex(index)]) {
      this.swap(index, parentIndex(index));
      index = parentIndex(index);
    }
  }

  private void swap(int i, int j) {
    int tmp = this.array[i];
    this.array[i] = this.array[j];
    this.array[j] = tmp;
  }


  private int parentIndex(int index) {
    return index / 2;
  }

  private int leftIndex(int index) {
    return 2 * index;
  }

  private int rightIndex(int index) {
    return 2 * index + 1;
  }


  private final int[] array;

  private int size = 0;

  private int capacity;

}
