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
    int middleIndex = arr.length / 2;
    handle(arr, 0, middleIndex);
    handle(arr, middleIndex + 1, arr.length);
    return arr;
  }


  private void handle(@NonNull T[] target, int startIndex, int endIndex) {

  }


}
