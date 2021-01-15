package com.tank.ds;

public class IntHeap2 {

  public IntHeap2(int[] array) {
    this.array = array;
  }


  public void upAdjust() {
    int childIndex = this.array.length - 1;
    int parentIndex = (childIndex - 1) / 2;
    int tmp = this.array[childIndex];
    while (childIndex > 0 && tmp < this.array[parentIndex]) {
      swap(childIndex, parentIndex);
      childIndex = parentIndex;
      parentIndex = (parentIndex - 1) / 2;
    }
  }

  private void swap(int i, int j) {
    int tmp = this.array[i];
    this.array[i] = this.array[j];
    this.array[j] = tmp;
  }

  public int[] toAbtain() {
    return this.array;
  }

  private int[] array;
}
