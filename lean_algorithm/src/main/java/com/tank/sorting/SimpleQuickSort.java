package com.tank.sorting;

import lombok.NonNull;

public class SimpleQuickSort {

  public void sort(@NonNull final Integer[] source, int startIndex, int endIndex) {

    if (startIndex >= endIndex) {
      return;
    }
    Integer pivotIndex = partition(source, startIndex, endIndex);
    this.sort(source, startIndex, pivotIndex - 1);
    this.sort(source, pivotIndex + 1, endIndex);
  }


  public Integer partition(Integer[] inputs, int startIndex, int endIndex) {

    int mark = startIndex;
    int pivot = inputs[startIndex];
    for (int index = startIndex + 1; index <= endIndex; index++) {
      int current = inputs[index];
      if (current > pivot) {
        mark++;
        int tmp = inputs[mark];
        inputs[mark] = inputs[index];
        inputs[index] = tmp;
      }
    }
    inputs[startIndex] = inputs[mark];
    inputs[mark] = pivot;
    return mark;
  }

}
