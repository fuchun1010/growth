package com.tank.renew.my20210328;

import com.google.common.base.Preconditions;
import lombok.NonNull;

import java.util.Objects;


/**
 * implements notes
 * split and compare sort for array
 * /
 * <p>
 * /**
 *
 * @param <T>
 * @author tank198435163.com
 */
public class QuickSortV1Impl<T extends Comparable<T>> implements QuickSortDef<T> {

  @Override
  public T[] sort(@NonNull T[] arr) {
    Preconditions.checkArgument(Objects.nonNull(arr), "array not allowed empty");
    Preconditions.checkArgument(arr.length > 0, "array content not allowed empty");
    this.handle(arr, 0, arr.length);
    return arr;
  }


  private void handle(@NonNull T[] target, int startIndex, int endIndex) {
    if (startIndex >= endIndex) {
      return;
    }
    int markIndex = this.pivotIndex(target, startIndex, endIndex);
    this.handle(target, startIndex, markIndex);
    this.handle(target, markIndex + 1, endIndex);
  }

  private int pivotIndex(@NonNull final T[] array, int startIndex, int endIndex) {

    T pivot = array[startIndex];
    int mark = startIndex;

    for (int index = startIndex + 1; index < endIndex; index++) {
      T indexValue = array[index];
      if (indexValue.compareTo(pivot) < 0) {
        mark++;
        T tmp = array[index];
        array[index] = array[mark];
        array[mark] = tmp;
      }
    }

    array[startIndex] = array[mark];
    array[mark] = pivot;
    return mark;
  }


}
